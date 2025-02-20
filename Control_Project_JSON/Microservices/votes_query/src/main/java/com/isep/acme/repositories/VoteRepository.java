package com.isep.acme.repositories;

import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface VoteRepository extends Neo4jRepository<Review, Long> {

    List<Vote> findVotesByReviewId(Long reviewId);

    List<Vote> findUpVotesByReviewId(Long reviewId);

    List<Vote> findDownVotesByReviewId(Long reviewId);
}
