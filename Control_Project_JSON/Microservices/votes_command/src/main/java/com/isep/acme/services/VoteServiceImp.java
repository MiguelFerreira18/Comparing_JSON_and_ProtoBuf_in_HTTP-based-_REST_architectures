package com.isep.acme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.VoteRepository;

@Service
public class VoteServiceImp implements IVoteService{

    @Autowired
    ReviewRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    VoteRepository voteRepository; 
    
    public VoteReviewDTO upvoteReview(Long reviewId, Long userId) {

        return voteReview(reviewId, userId, "upVote");
    }

    public VoteReviewDTO downvoteReview(Long reviewId, Long userId) {
        
        return voteReview(reviewId, userId, "downVote");
    }

    private VoteReviewDTO voteReview(Long reviewId, Long userId, String voteType) {

        Optional<Review> reviewOptional = this.repository.findById(reviewId);
    
        if (reviewOptional.isEmpty()) return null;
    
        Review review = reviewOptional.get();
    
        Vote vote = new Vote(voteType, userId);

        boolean added = "upVote".equals(voteType) ? review.addUpVote(vote) : review.addDownVote(vote);
    
        if (added) {
            vote.setReview(review);
            voteRepository.save(vote);
            // Review reviewUpdated = this.repository.save(review);
            return new VoteReviewDTO(vote.getUserID(), voteType, vote.getReview().getIdReview());
        }
    
        return null;
    }
}
