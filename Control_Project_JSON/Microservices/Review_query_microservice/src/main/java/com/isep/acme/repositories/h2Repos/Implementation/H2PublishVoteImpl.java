package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.model.H2Entity.PublishVote;
import com.isep.acme.repositories.h2Repos.Repos.PublishingsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class H2PublishVoteImpl {

    @Autowired
    private PublishingsRepository publishRepo;

    PublishVote findByReviewIdAndUserId(Long reviewId, Long userId) {
        return publishRepo.findByReviewIdAndUserId(reviewId, userId);
    }

    void deleteByReviewIdAndUserId(Long reviewId, Long userId) {
        publishRepo.deleteByReviewIdAndUserId(reviewId, userId);
    }
}
