package com.isep.acme.services;

import com.isep.acme.model.H2Entity.PublishVote;
import com.isep.acme.repositories.h2Repos.Repos.PublishingsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishVoteServiceImpl implements PublishVoteService {

    @Autowired
    PublishingsRepository publishRepo;

    @Override
    public PublishVote findByReviewIdAndUserId(Long reviewId, Long userId) {
        return publishRepo.findByReviewIdAndUserId(reviewId, userId);
    }

    @Override
    public void deleteByReviewIdAndUserId(Long reviewId, Long userId) {
        publishRepo.deleteByReviewIdAndUserId(reviewId, userId);
    }

    @Override
    public void save(PublishVote publishVote) {
        publishRepo.save(publishVote);
    }
}
