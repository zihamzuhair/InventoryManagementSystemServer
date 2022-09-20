package com.ds.server.product;

import com.ds.server.InventoryManagementServer;
import ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse;
import ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityServiceGrpc;

public class CheckProductQuantityServiceImpl extends CheckProductQuantityServiceGrpc.CheckProductQuantityServiceImplBase {

    private InventoryManagementServer server;

    public CheckProductQuantityServiceImpl(InventoryManagementServer server){
        this.server = server;
    }

    @Override
    public void checkProductQuantity(ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityRequest request,
                             io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckProductQuantityResponse> responseObserver) {

        String accountId = request.getProductId();
        System.out.println("Request received..");
        double balance = getProductQuantity(accountId);
        CheckProductQuantityResponse response = CheckProductQuantityResponse
                .newBuilder()
                .setProductQuantity(balance)
                .build();
        System.out.println("Responding, balance for account " + accountId + " is " + balance);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private double getProductQuantity(String accountId) {
        return server.getProductQuantity(accountId);
    }
}
