package com.isep.acme.model;

import com.isep.acme.model.H2Entity.Product;

public class ProductMessage {
    private Product product;
    private String message;
    private int senderID;

    public ProductMessage() {
    }
    public ProductMessage(String message, Product product) {
        this.product = product;
        this.message = message;
    }

    public ProductMessage(String message, Integer senderID, Product product) {
        this.product = product;
        this.message = message;
        this.senderID = senderID;
    }

    public int getSenderID() {
        return senderID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductMessage{");
        sb.append("message='").append(message).append('\'');
        sb.append(", senderID=").append(senderID);
        sb.append('}');
        return sb.toString();
    }
}
