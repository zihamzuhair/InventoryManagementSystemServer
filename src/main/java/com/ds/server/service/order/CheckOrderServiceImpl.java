package com.ds.server.service.order;

import com.ds.server.InventoryManagementServer;
import com.ds.server.models.Order;
import ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse;
import ds.inventoryManagementSystem.grpc.generated.CheckOrderServiceGrpc;

public class CheckOrderServiceImpl extends CheckOrderServiceGrpc.CheckOrderServiceImplBase {

    private InventoryManagementServer server;

    public CheckOrderServiceImpl(InventoryManagementServer server) {
        this.server = server;
    }

    @Override
    public void checkOrders(ds.inventoryManagementSystem.grpc.generated.CheckOrderRequest request,
                            io.grpc.stub.StreamObserver<ds.inventoryManagementSystem.grpc.generated.CheckOrderResponse> responseObserver) {

        String orderId = request.getOrderId();
        System.out.println("Request received..");
        Order order = getOrderItem(orderId);

        CheckOrderResponse response = CheckOrderResponse
                .newBuilder()
                .setItemDescription(order.getItem())
                .setItemQuantity(order.getQuantity())
                .build();
        System.out.println("Responding, quantity: " + order.getQuantity() + " and item: " + order.getItem() + " for Order no : " + orderId);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Order getOrderItem(String orderId) {
        return server.getOrderProduct(orderId);
    }

}
