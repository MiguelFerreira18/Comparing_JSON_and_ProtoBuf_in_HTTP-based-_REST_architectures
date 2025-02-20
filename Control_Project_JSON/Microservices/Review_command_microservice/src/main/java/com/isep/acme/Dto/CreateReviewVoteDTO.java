package com.isep.acme.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewVoteDTO {

    CreateReviewDTO createReviewDTO;
    VoteReviewDTO voteReviewDTO;
}
