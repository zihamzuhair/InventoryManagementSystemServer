package com.ds.server.models;

public class OrderResponse {

    String Id;
    Boolean status;

    public String getId() {
        return Id;
    }

    double AvailableQuantity;

    public void setId(String id) {
        Id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public double getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(double availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

}
