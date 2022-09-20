package com.ds.server.order;

import com.ds.server.InventoryManagementServer;
import com.ds.server.models.Order;
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
        Order order = getOrderProduct(orderId);


        CheckOrderResponse response = CheckOrderResponse
                .newBuilder()
                .setProductName(order.getProduct())
                .setProductQuantity(order.getQuantity())
                .build();
        System.out.println("Responding, quantity: "+order.getQuantity()+" and product: "+order.getProduct()+" for Order no : " + orderId);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Order getOrderProduct(String orderId) {
        return server.getOrderProduct(orderId);
    }

}
