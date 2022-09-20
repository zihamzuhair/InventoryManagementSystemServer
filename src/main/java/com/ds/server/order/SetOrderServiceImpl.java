//package com.ds.server.order;
//
//
//import com.ds.server.InventoryManagementServer;
//import com.ds.server.models.Product;
//import com.ds.sycnhronization.DistributedTxCoordinator;
//import com.ds.sycnhronization.DistributedTxListener;
//import com.ds.sycnhronization.DistributedTxParticipant;
//import ds.inventoryManagementSystem.grpc.generated.*;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import javafx.util.Pair;
//import org.apache.zookeeper.KeeperException;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
//public class SetOrderServiceImpl extends SetOrderServiceGrpc.SetOrderServiceImplBase implements DistributedTxListener {
//    private ManagedChannel channel = null;
//    SetOrderServiceGrpc.SetOrderServiceBlockingStub clientStub = null;
//    private InventoryManagementServer server;
//    private Pair<String, Product> tempDataHolder;
//    private boolean transactionStatus = false;
//
//    public SetOrderServiceImpl(InventoryManagementServer server) {
//        this.server = server;
//    }
//
//    @Override
//    public void setOrders(SetOrderRequest request,io.grpc.stub.StreamObserver<SetOrderResponse> responseObserver) {
//
//        System.out.println("inside setOrders");
//        String orderId = request.getOrderId();
//        double productQuantity = request.getProductQuantity();
//        String productName = request.getProductName();
//
//        Product product = new Product();
//        product.setProductId(productName);
//        product.setQuantity(productQuantity);
//
//        System.out.println(product);
//
//        if (server.isLeader()) {
//            // Act as primary
//            try {
//                System.out.println("Updating account balance as Primary");
//
//                startDistributedTx(orderId, product);
//                updateSecondaryServers(orderId, productQuantity, productName);
//                System.out.println("going to perform");
//                if (productQuantity > 0) {
//                    ((DistributedTxCoordinator) server.getTransaction()).perform();
//                } else {
//                    ((DistributedTxCoordinator) server.getTransaction()).sendGlobalAbort();
//                }
//            } catch (Exception e) {
//                System.out.println("Error while updating the account balance" + e.getMessage());
//                e.printStackTrace();
//            }
//        } else {
//            // Act As Secondary
//            if (request.getIsSentByPrimary()) {
//                System.out.println("Updating account balance on secondary, on Primary 's command");
//                startDistributedTx(orderId, product);
//                if (productQuantity != 0.0d) {
//                    ((DistributedTxParticipant) server.getTransaction()).voteCommit();
//                } else {
//                    ((DistributedTxParticipant) server.getTransaction()).voteAbort();
//                }
//            } else {
//                SetOrderResponse response = callPrimary(orderId, productQuantity,productName);
//                if (response.getStatus()) {
//                    transactionStatus = true;
//                }
//            }
//
//        }
//
//
//        SetOrderResponse response = SetOrderResponse
//                .newBuilder()
//                .setStatus(transactionStatus)
//                .build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//
//    private void startDistributedTx(String orderId, Product product) {
//        try {
//            server.getTransaction().start(orderId, String.valueOf(UUID.randomUUID()));
//            tempDataHolder = new Pair<>(orderId, product);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateBalance() {
//        System.out.println("-------------");
//        System.out.println(tempDataHolder.getKey());
//        System.out.println(tempDataHolder.getValue());
//        System.out.println("-------------");
//        if (tempDataHolder != null) {
//            String orderId = tempDataHolder.getKey();
//            Product product = tempDataHolder.getValue();
//
//            System.out.println("set order called");
//            server.setOrders(orderId, product);
//            System.out.println("set order finished");
//
//            System.out.println("Order " + orderId + " updated to productQuantity " + product.getQuantity() + " committed");
//            tempDataHolder = null;
//        }
//    }
//
//    private SetOrderResponse callServer(String orderId, double productQuantity, String productName, boolean isSentByPrimary, String IPAddress, int port) {
//        System.out.println("Call Server " + IPAddress + ":" + port);
//        channel = ManagedChannelBuilder.forAddress(IPAddress, port)
//                .usePlaintext()
//                .build();
//        clientStub = SetOrderServiceGrpc.newBlockingStub(channel);
//
//        SetOrderRequest request = SetOrderRequest
//                .newBuilder()
//                .setOrderId(orderId)
//                .setProductQuantity(productQuantity)
//                .setProductName(productName)
//                .setIsSentByPrimary(isSentByPrimary)
//                .build();
//        SetOrderResponse response = clientStub.setOrders(request);
//        return response;
//    }
//
//    private SetOrderResponse callPrimary(String orderId, double productQuantity, String productName) {
//        System.out.println("Calling Primary server");
//        String[] currentLeaderData = server.getCurrentLeaderData();
//        String IPAddress = currentLeaderData[0];
//        int port = Integer.parseInt(currentLeaderData[1]);
//        return callServer(orderId, productQuantity, productName, false, IPAddress, port);
//    }
//
//    private void updateSecondaryServers(String orderId, double productQuantity, String productName) throws KeeperException, InterruptedException {
//        System.out.println("Updating other servers");
//        List<String[]> othersData = server.getOthersData();
//        for (String[] data : othersData) {
//            String IPAddress = data[0];
//            int port = Integer.parseInt(data[1]);
//            callServer(orderId, productQuantity,productName, true, IPAddress, port);
//        }
//    }
//
//    @Override
//    public void onGlobalCommit() {
//        updateBalance();
//    }
//
//    @Override
//    public void onGlobalAbort() {
//        tempDataHolder = null;
//        System.out.println("Transaction Aborted by the Coordinator");
//    }
//}



package com.ds.server.order;

import com.ds.server.InventoryManagementServer;
import com.ds.server.models.Order;
import com.ds.sycnhronization.DistributedTxCoordinator;
import com.ds.sycnhronization.DistributedTxListener;
import com.ds.sycnhronization.DistributedTxParticipant;
import ds.inventoryManagementSystem.grpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.util.Pair;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SetOrderServiceImpl extends SetOrderServiceGrpc.SetOrderServiceImplBase implements DistributedTxListener {

    private ManagedChannel channel = null;
    SetOrderServiceGrpc.SetOrderServiceBlockingStub clientStub = null;
    private InventoryManagementServer server;
    private Pair<String, Double> tempDataHolder;
    private boolean transactionStatus = false;
    private Order newOrder;

    public SetOrderServiceImpl(InventoryManagementServer server) {
        this.server = server;
    }


    @Override
    public void setOrders(ds.inventoryManagementSystem.grpc.generated.SetOrderRequest request,
                                   io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.SetOrderResponse> responseObserver) {

        String orderId = request.getOrderId();
//        String productsId =  "apple"; //request.getProductId();
        String productsId =  request.getProductName();
        double quantity = request.getProductQuantity();
        if (server.isLeader()) {
            // Act as primary
            try {
                System.out.println("Updating account balance as Primary");
//                startDistributedTx(orderId, quantity);
                startDistributedTx(orderId, productsId, quantity);
//                updateSecondaryServers(orderId, quantity);
                updateSecondaryServers(orderId, productsId, quantity);
                System.out.println("going to perform");
                if (quantity > 0) {
                    ((DistributedTxCoordinator) server.getTransaction()).perform();
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
//                startDistributedTx(productsId, quantity);
                startDistributedTx(orderId, productsId, quantity);
                if (quantity != 0.0d) {
                    ((DistributedTxParticipant) server.getTransaction()).voteCommit();
                } else {
                    ((DistributedTxParticipant) server.getTransaction()).voteAbort();
                }
            } else {
                SetOrderResponse response = callPrimary(orderId, productsId, quantity);
                if (response.getStatus()) {
                    transactionStatus = true;
                }
            }
        }
        SetOrderResponse response = SetOrderResponse
                .newBuilder()
                .setStatus(transactionStatus)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

//    private void startDistributedTx(String productsId, double quantity) {
//        try {
//            server.getTransaction().start(productsId, String.valueOf(UUID.randomUUID()));
//            tempDataHolder = new Pair<>(productsId, quantity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void startDistributedTx(String orderId, String productsId, double quantity) {
        try {
            server.getTransaction().start(productsId, String.valueOf(UUID.randomUUID()));
//            tempDataHolder = new Pair<>(productsId, quantity);
            newOrder = new Order();
            newOrder.setOrderId(orderId);
            newOrder.setProduct(productsId);
            newOrder.setQuantity(quantity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void updateBalance() {
//
//        System.out.println("-------------");
//        System.out.println(tempDataHolder.getKey());
//        System.out.println(tempDataHolder.getValue());
//        System.out.println("-------------");
//
//        if (tempDataHolder != null) {
//            String productsId = tempDataHolder.getKey();
//            double quantity = tempDataHolder.getValue();
//
//            System.out.println("set Order called");
//            server.setOrders(productsId, quantity);
//            System.out.println("set Order finished");
//
//            System.out.println("Order " + productsId + " updated to quantity " + quantity + " committed");
//            tempDataHolder = null;
//        }
//    }

    private void updateBalance() {

        System.out.println("-------------");
        System.out.println("Order: " + newOrder.getOrderId());
        System.out.println("Product: " + newOrder.getProduct());
        System.out.println("Quantity: " + newOrder.getQuantity());
        System.out.println("-------------");

        if (newOrder != null) {
            System.out.println("set Order called");
            server.setOrders(newOrder);
            System.out.println("set Order finished");

            System.out.println("Order " + newOrder.getOrderId() + " updated to quantity " + newOrder.getQuantity() + " committed");
            tempDataHolder = null;
        }
    }


//    private SetOrderResponse callServer(String productsId, double quantity, boolean isSentByPrimary, String IPAddress, int port) {
//        System.out.println("Call Server " + IPAddress + ":" + port);
//        channel = ManagedChannelBuilder.forAddress(IPAddress, port)
//                .usePlaintext()
//                .build();
//        clientStub = SetOrderServiceGrpc.newBlockingStub(channel);
//
//        SetOrderRequest request = SetOrderRequest
//                .newBuilder()
//                .setOrderId(productsId)
//                .setProductName("apple")
//                .setProductQuantity(quantity)
//                .setIsSentByPrimary(isSentByPrimary)
//                .build();
//        SetOrderResponse response = clientStub.setOrders(request);
//        return response;
//    }


    private SetOrderResponse callServer(Order order, boolean isSentByPrimary, String IPAddress, int port) {
        System.out.println("Call Server " + IPAddress + ":" + port);
        channel = ManagedChannelBuilder.forAddress(IPAddress, port)
                .usePlaintext()
                .build();
        clientStub = SetOrderServiceGrpc.newBlockingStub(channel);

        SetOrderRequest request = SetOrderRequest
                .newBuilder()
                .setOrderId(order.getOrderId())
                .setProductName(order.getProduct())
                .setProductQuantity(order.getQuantity())
                .setIsSentByPrimary(isSentByPrimary)
                .build();
        SetOrderResponse response = clientStub.setOrders(request);
        return response;
    }

//    private SetOrderResponse callPrimary(String productsId, double quantity) {
//        System.out.println("Calling Primary server");
//        String[] currentLeaderData = server.getCurrentLeaderData();
//        String IPAddress = currentLeaderData[0];
//        int port = Integer.parseInt(currentLeaderData[1]);
//        return callServer(productsId, quantity, false, IPAddress, port);
//    }

    private SetOrderResponse callPrimary(String orderId, String productsId, double quantity) {
        System.out.println("Calling Primary server");
        String[] currentLeaderData = server.getCurrentLeaderData();
        String IPAddress = currentLeaderData[0];
        int port = Integer.parseInt(currentLeaderData[1]);
        Order order = CreateOrderObject(orderId, productsId, quantity);
        return callServer(order, false, IPAddress, port);
    }

//    private void updateSecondaryServers(String productsId, double quantity) throws KeeperException, InterruptedException {
//        System.out.println("Updating other servers");
//        List<String[]> othersData = server.getOthersData();
//        for (String[] data : othersData) {
//            String IPAddress = data[0];
//            int port = Integer.parseInt(data[1]);
//            callServer(productsId, quantity, true, IPAddress, port);
//        }
//    }

    private void updateSecondaryServers(String orderId, String productsId, double quantity) throws KeeperException, InterruptedException {
        System.out.println("Updating other servers");
        List<String[]> othersData = server.getOthersData();
        Order order = CreateOrderObject(orderId, productsId,quantity);
        for (String[] data : othersData) {
            String IPAddress = data[0];
            int port = Integer.parseInt(data[1]);
            callServer(order, true, IPAddress, port);
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

    private Order CreateOrderObject(String orderId, String productId, double quantity){
        Order order = new Order();
        order.setOrderId(orderId);
        order.setProduct(productId);
        order.setQuantity(quantity);
        return order;

    }
}
