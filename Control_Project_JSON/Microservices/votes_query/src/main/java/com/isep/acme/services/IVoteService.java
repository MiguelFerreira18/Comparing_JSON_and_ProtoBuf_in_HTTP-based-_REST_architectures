package com.isep.acme.services;

import java.util.List;

import com.isep.acme.dto.VoteDTO;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.model.Vote;

public interface IVoteService {
        
        VoteDTO getVotesByReviewId(Long reviewId);
    
        // List<Vote> getUpVotesByReviewId(Long reviewId);

        // List<Vote> getDownVotesByReviewId(Long reviewId);

        Vote createUpVote(VoteReviewDTO vote);
        Vote createDownVote(VoteReviewDTO vote);
}
