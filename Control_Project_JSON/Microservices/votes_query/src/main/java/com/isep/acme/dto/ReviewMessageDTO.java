package com.isep.acme.dto;

public class ReviewMessageDTO {
    private ReviewDTO review;
    private String message;
    private int senderID;

    public ReviewMessageDTO() {
    }

    public ReviewMessageDTO(ReviewDTO review, String message, int senderID) {
        this.review = review;
        this.message = message;
        this.senderID = senderID;
    }

    public ReviewDTO getReview() {
        return review;
    }

    public void setReview(ReviewDTO review) {
        this.review = review;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }
}
