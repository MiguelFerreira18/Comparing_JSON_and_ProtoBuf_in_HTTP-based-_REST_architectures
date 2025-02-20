package com.isep.acme.model;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;

public class ProductApprovalMessage {

    private ProductAcceptanceEvent product;
    private String message;
    private int senderID;

    public ProductApprovalMessage() {
    }
    public ProductApprovalMessage(String message, ProductAcceptanceEvent product) {
        this.product = product;
        this.message = message;
    }

    public ProductApprovalMessage(String message, Integer senderID, ProductAcceptanceEvent product) {
        this.product = product;
        this.message = message;
        this.senderID = senderID;
    }

    public int getSenderID() {
        return senderID;
    }

    public ProductAcceptanceEvent getProduct() {
        return product;
    }

    public void setProduct(ProductAcceptanceEvent product) {
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
