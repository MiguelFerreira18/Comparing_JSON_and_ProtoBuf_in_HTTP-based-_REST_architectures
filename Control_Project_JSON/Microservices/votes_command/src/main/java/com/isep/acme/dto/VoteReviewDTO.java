package com.isep.acme.dto;

import lombok.Data;

@Data
public class VoteReviewDTO {

    private Long userID;
    private String vote;
    private Long reviewID;

    // Default constructor
    public VoteReviewDTO() {
    }

    public VoteReviewDTO(Long userID, String vote, Long reviewID) {
        this.userID = userID;
        this.vote = vote;
        this.reviewID = reviewID;
    }
}