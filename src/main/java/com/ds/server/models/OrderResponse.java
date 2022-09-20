package com.ds.server.models;

public class OrderResponse {
    String Id;
    Boolean status;
    double AvailableQuantity;

    public String getId() {
        return Id;
    }

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
