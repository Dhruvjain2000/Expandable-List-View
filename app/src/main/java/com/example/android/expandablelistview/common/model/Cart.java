package com.example.android.expandablelistview.common.model;

public class Cart {
    String user;
    String status;
    String product;

    public Cart() {

    }

    public Cart(String user, String status, String product) {
        this.user = user;
        this.status = status;
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
