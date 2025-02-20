package com.isep.acme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.isep.acme.model.Review;

public interface ReviewRepository  extends Neo4jRepository<Review, Long> {
    
    @Query("MATCH (r:Review) WHERE r.reviewId = $reviewId RETURN r")
    Review findByReviewId(Long reviewId);
}
