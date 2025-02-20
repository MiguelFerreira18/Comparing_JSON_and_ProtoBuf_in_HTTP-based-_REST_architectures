package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.PublishVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PublishingsRepository extends CrudRepository<PublishVote, Long> {

    @Query("SELECT p FROM PublishVote p WHERE p.ReviewId=:reviewId AND p.UserId=:userId")
    PublishVote findByReviewIdAndUserId(Long reviewId, Long userId);

    @Query("DELETE FROM PublishVote p WHERE p.ReviewId=:reviewId AND p.UserId=:userId")
    void deleteByReviewIdAndUserId(Long reviewId, Long userId);
}
