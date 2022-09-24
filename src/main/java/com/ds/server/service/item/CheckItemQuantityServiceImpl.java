package com.ds.server.service.item;

import com.ds.server.InventoryManagementServer;
import ds.inventoryManagementSystem.grpc.generated.CheckItemQuantityResponse;
import ds.inventoryManagementSystem.grpc.generated.CheckItemQuantityServiceGrpc;

public class CheckItemQuantityServiceImpl extends CheckItemQuantityServiceGrpc.CheckItemQuantityServiceImplBase {

    private InventoryManagementServer server;

    public CheckItemQuantityServiceImpl(InventoryManagementServer server) {
        this.server = server;
    }

    @Override
    public void checkProductQuantity(ds.inventoryManagementSystem.grpc.generated.CheckItemQuantityRequest request,
                                     io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckItemQuantityResponse> responseObserver) {

        String item = request.getItemDescription();
        System.out.println("Request received..");
        double balance = getItemQuantity(item);
        CheckItemQuantityResponse response = CheckItemQuantityResponse
                .newBuilder()
                .setItemQuantity(balance)
                .build();
        System.out.println("Responding, balance for item " + item + " is " + balance);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private double getItemQuantity(String item) {
        return server.getItemQuantity(item);
    }
}
