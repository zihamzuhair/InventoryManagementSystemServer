package com.ds.server.product;


import com.ds.server.InventoryManagementServer;
import com.ds.sycnhronization.DistributedTxCoordinator;
import com.ds.sycnhronization.DistributedTxListener;
import com.ds.sycnhronization.DistributedTxParticipant;
import ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest;
import ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse;
import ds.inventoryManagementSystem.grpc.generated.SetProductQuantityServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.util.Pair;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SetProductQuantityServiceImpl extends SetProductQuantityServiceGrpc.SetProductQuantityServiceImplBase implements DistributedTxListener {
    private ManagedChannel channel = null;
    SetProductQuantityServiceGrpc.SetProductQuantityServiceBlockingStub clientStub = null;
    private InventoryManagementServer server;
    private Pair<String, Double> tempDataHolder;
    private boolean transactionStatus = false;

    public SetProductQuantityServiceImpl(InventoryManagementServer server) {
        this.server = server;
    }

    @Override
    public void setProductQuantity(ds.inventoryManagementSystem.grpc.generated.SetProductQuantityRequest request,
                           io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetProductQuantityResponse> responseObserver) {

        String productsId = request.getProductId();
        double quantity = request.getQuantity();
        if (server.isLeader()) {
            // Act as primary
            try {
                System.out.println("Updating account balance as Primary");
                startDistributedTx(productsId, quantity);
                updateSecondaryServers(productsId, quantity);
                System.out.println("going to perform");
                if (quantity > 0) {
                    ((DistributedTxCoordinator) server.getTransaction()).perform();
                    transactionStatus = true;
                } else {
                    ((DistributedTxCoordinator) server.getTransaction()).sendGlobalAbort();
                }
            } catch (Exception e) {
                System.out.println("Error while updating the account balance" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            // Act As Secondary
            if (request.getIsSentByPrimary()) {
                System.out.println("Updating account balance on secondary, on Primary 's command");
                startDistributedTx(productsId, quantity);
                if (quantity != 0.0d) {
                    ((DistributedTxParticipant) server.getTransaction()).voteCommit();
                    transactionStatus = true;
                } else {
                    ((DistributedTxParticipant) server.getTransaction()).voteAbort();
                }
            } else {
                SetProductQuantityResponse response = callPrimary(productsId, quantity);
                if (response.getStatus()) {
                    transactionStatus = true;
                }
            }
        }
        SetProductQuantityResponse response = SetProductQuantityResponse
                .newBuilder()
                .setStatus(transactionStatus)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void startDistributedTx(String productsId, double quantity) {
        try {
            server.getTransaction().start(productsId, String.valueOf(UUID.randomUUID()));
            tempDataHolder = new Pair<>(productsId, quantity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBalance() {
        if (tempDataHolder != null) {
            String productsId = tempDataHolder.getKey();
            double quantity = tempDataHolder.getValue();

            server.setProductQuantity(productsId, quantity);

            System.out.println("product " + productsId + " updated to quantity " + quantity + " committed");
            tempDataHolder = null;
        }
    }

    private SetProductQuantityResponse callServer(String productsId, double quantity, boolean isSentByPrimary, String IPAddress, int port) {
        System.out.println("Call Server " + IPAddress + ":" + port);
        channel = ManagedChannelBuilder.forAddress(IPAddress, port)
                .usePlaintext()
                .build();
        clientStub = SetProductQuantityServiceGrpc.newBlockingStub(channel);

        SetProductQuantityRequest request = SetProductQuantityRequest
                .newBuilder()
                .setProductId(productsId)
                .setQuantity(quantity)
                .setIsSentByPrimary(isSentByPrimary)
                .build();
        SetProductQuantityResponse response = clientStub.setProductQuantity(request);
        return response;
    }

    private SetProductQuantityResponse callPrimary(String productsId, double quantity) {
        System.out.println("Calling Primary server");
        String[] currentLeaderData = server.getCurrentLeaderData();
        String IPAddress = currentLeaderData[0];
        int port = Integer.parseInt(currentLeaderData[1]);
        return callServer(productsId, quantity, false, IPAddress, port);
    }

    private void updateSecondaryServers(String productsId, double quantity) throws KeeperException, InterruptedException {
        System.out.println("Updating other servers");
        List<String[]> othersData = server.getOthersData();
        for (String[] data : othersData) {
            String IPAddress = data[0];
            int port = Integer.parseInt(data[1]);
            callServer(productsId, quantity, true, IPAddress, port);
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
