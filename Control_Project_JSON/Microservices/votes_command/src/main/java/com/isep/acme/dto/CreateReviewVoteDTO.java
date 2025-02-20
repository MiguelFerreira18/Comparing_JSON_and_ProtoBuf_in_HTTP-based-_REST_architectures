package com.isep.acme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewVoteDTO {

    ReviewDTO reviewDTO;
    VoteReviewDTO voteReviewDTO;
}
