package com.ds.server.service.item;


import com.ds.server.InventoryManagementServer;
import com.ds.sycnhronization.DistributedTxCoordinator;
import com.ds.sycnhronization.DistributedTxListener;
import com.ds.sycnhronization.DistributedTxParticipant;
import ds.inventoryManagementSystem.grpc.generated.SetItemQuantityRequest;
import ds.inventoryManagementSystem.grpc.generated.SetItemQuantityResponse;
import ds.inventoryManagementSystem.grpc.generated.SetItemQuantityServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.util.Pair;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SetItemQuantityServiceImpl extends SetItemQuantityServiceGrpc.SetItemQuantityServiceImplBase implements DistributedTxListener {
    private ManagedChannel channel = null;
    SetItemQuantityServiceGrpc.SetItemQuantityServiceBlockingStub clientStub = null;
    private InventoryManagementServer server;
    private Pair<String, Double> tempDataHolder;
    private boolean transactionStatus = false;

    public SetItemQuantityServiceImpl(InventoryManagementServer server) {
        this.server = server;
    }

    @Override
    public void setProductQuantity(ds.inventoryManagementSystem.grpc.generated.SetItemQuantityRequest request,
                                   io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetItemQuantityResponse> responseObserver) {

        String itemDescription = request.getItemDescription();
        double quantity = request.getQuantity();
        if (server.isLeader()) {
            // Act as primary
            try {
                System.out.println("Updating item balance as Primary");
                startDistributedTx(itemDescription, quantity);
                updateSecondaryServers(itemDescription, quantity);
                System.out.println("going to perform");
                if (quantity > 0) {
                    ((DistributedTxCoordinator) server.getItemTransaction()).perform();
                    transactionStatus = true;
                } else {
                    ((DistributedTxCoordinator) server.getItemTransaction()).sendGlobalAbort();
                }
            } catch (Exception e) {
                System.out.println("Error while updating the item balance" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            // Act As Secondary
            if (request.getIsSentByPrimary()) {
                System.out.println("Updating item balance on secondary, on Primary 's command");
                startDistributedTx(itemDescription, quantity);
                if (quantity != 0.0d) {
                    ((DistributedTxParticipant) server.getItemTransaction()).voteCommit();
                    transactionStatus = true;
                } else {
                    ((DistributedTxParticipant) server.getItemTransaction()).voteAbort();
                }
            } else {
                SetItemQuantityResponse response = callPrimary(itemDescription, quantity);
                if (response.getStatus()) {
                    transactionStatus = true;
                }
            }
        }
        SetItemQuantityResponse response = SetItemQuantityResponse
                .newBuilder()
                .setStatus(transactionStatus)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void startDistributedTx(String itemDescription, double quantity) {
        try {
            server.getItemTransaction().start(itemDescription, String.valueOf(UUID.randomUUID()));
            tempDataHolder = new Pair<>(itemDescription, quantity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBalance() {
        if (tempDataHolder != null) {
            String itemDescription = tempDataHolder.getKey();
            double quantity = tempDataHolder.getValue();

            server.setItemQuantity(itemDescription, quantity);

            System.out.println("Item " + itemDescription + " updated to quantity " + quantity + " committed");
            tempDataHolder = null;
        }
    }

    private SetItemQuantityResponse callServer(String itemDescription, double quantity, boolean isSentByPrimary, String IPAddress, int port) {
        System.out.println("Call Server " + IPAddress + ":" + port);
        channel = ManagedChannelBuilder.forAddress(IPAddress, port)
                .usePlaintext()
                .build();
        clientStub = SetItemQuantityServiceGrpc.newBlockingStub(channel);

        SetItemQuantityRequest request = SetItemQuantityRequest
                .newBuilder()
                .setItemDescription(itemDescription)
                .setQuantity(quantity)
                .setIsSentByPrimary(isSentByPrimary)
                .build();
        SetItemQuantityResponse response = clientStub.setProductQuantity(request);
        return response;
    }

    private SetItemQuantityResponse callPrimary(String itemDescription, double quantity) {
        System.out.println("Calling Primary server");
        String[] currentLeaderData = server.getCurrentLeaderData();
        String IPAddress = currentLeaderData[0];
        int port = Integer.parseInt(currentLeaderData[1]);
        return callServer(itemDescription, quantity, false, IPAddress, port);
    }

    private void updateSecondaryServers(String itemDescription, double quantity) throws KeeperException, InterruptedException {
        System.out.println("Updating other servers");
        List<String[]> othersData = server.getOthersData();
        for (String[] data : othersData) {
            String IPAddress = data[0];
            int port = Integer.parseInt(data[1]);
            callServer(itemDescription, quantity, true, IPAddress, port);
        }
    }

    @Override
    public void onGlobalCommit() {
        updateBalance();
    }

    @Override
    public void onGlobalAbort() {
        tempDataHolder = null;
        System.out.println("Transaction Aborted by the Coordinator");
    }
}
