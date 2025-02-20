package com.isep.acme.services;

import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.model.Review;

public interface IReviewService {
    
    Review createReview(VoteReviewDTO review);
}
