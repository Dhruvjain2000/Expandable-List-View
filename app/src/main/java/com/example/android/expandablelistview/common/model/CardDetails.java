package com.example.android.expandablelistview.common.model;

public class CardDetails {
    String userId,Product,key;

    public CardDetails(String userId, String product, String key) {
        this.userId = userId;
        Product = product;
        this.key = key;
    }

    public CardDetails() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
