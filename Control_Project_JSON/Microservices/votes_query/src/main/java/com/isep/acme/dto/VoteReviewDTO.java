package com.isep.acme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteReviewDTO {

    private Long userID;
    private String vote;
    private Long reviewID;

    // Default constructor
    public VoteReviewDTO() {
    }
    public VoteReviewDTO(Long userID, String vote, Long reviewId) {
        this.userID = userID;
        this.vote = vote;
        this.reviewID = reviewId;
    }
}