package com.isep.acme.services;

import com.isep.acme.model.H2Entity.PublishVote;

public interface PublishVoteService {

    PublishVote findByReviewIdAndUserId(Long reviewId, Long userId);

    void deleteByReviewIdAndUserId(Long reviewId, Long userId);

    void save(PublishVote publishVote);
}
