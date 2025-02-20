package com.isep.acme.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVoteRespDTO {
    ReviewDTO reviewDTO;

    VoteReviewDTO voteReviewDTO;

    public ReviewVoteRespDTO(){

    }

    public ReviewVoteRespDTO(ReviewDTO reviewDTO, VoteReviewDTO voteReviewDTO){
        this.reviewDTO = reviewDTO;
        this.voteReviewDTO = voteReviewDTO;
    }
}
