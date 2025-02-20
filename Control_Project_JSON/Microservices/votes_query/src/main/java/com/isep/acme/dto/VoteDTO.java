package com.isep.acme.dto;

import lombok.Data;

@Data
public class VoteDTO {
    
    public int upVotes;
    public int downVotes;
    public Long reviewId;

    public VoteDTO() {
    }

    public VoteDTO(int upVotes, int downVotes, Long reviewId) {
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.reviewId = reviewId;
    }
}
