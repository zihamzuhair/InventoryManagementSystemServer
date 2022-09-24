package com.ds.server.models;

public class Order {

    String OrderId;
    String item;
    double Quantity;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }
}
