package com.ds.server.order;

import com.ds.server.InventoryManagementServer;
import ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse;
import ds.inventoryManagementSystem.grpc.generated.CheckOrderServiceGrpc;

public class CheckOrderServiceImpl extends CheckOrderServiceGrpc.CheckOrderServiceImplBase {

    private InventoryManagementServer server;

    public CheckOrderServiceImpl(InventoryManagementServer server){
        this.server = server;
    }

    @Override
    public void checkOrders(ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest request,
                                     io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> responseObserver) {

        String orderId = request.getOrderId() ;
        System.out.println("Request received..");
        double qty = getOrderProduct(orderId);


        CheckOrderResponse response = CheckOrderResponse
                .newBuilder()
                .setProductName("apple")
                .setProductQuantity(qty)
                .build();
       System.out.println("Responding, balance for account " + orderId + " is " + qty);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private double getOrderProduct(String orderId) {
        return server.getOrderProduct(orderId);
    }

}
