package com.isep.acme.model;

import com.isep.acme.model.H2Entity.Review;

public class ReviewMessage {
    private Review review;
    private String message;
    private int senderID;

    public ReviewMessage() {
    }

    public ReviewMessage(String message, Review review) {
        this.review = review;
        this.message = message;
    }

    public ReviewMessage(String message, Integer senderID, Review review) {
        this.review = review;
        this.message = message;
        this.senderID = senderID;
    }

    public int getSenderID() {
        return senderID;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReviewMessage{");
        sb.append("message='").append(message).append('\'');
        sb.append(", senderID=").append(senderID);
        sb.append('}');
        return sb.toString();
    }


}
