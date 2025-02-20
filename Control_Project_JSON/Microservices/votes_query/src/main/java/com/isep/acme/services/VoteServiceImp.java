package com.isep.acme.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.consumer.VoteConsumer;
import com.isep.acme.dto.VoteDTO;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ReviewRepository;

@Service
public class VoteServiceImp implements IVoteService {
    

    @Autowired
    private ReviewRepository reviewRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);

    public VoteDTO getVotesByReviewId(Long reviewId){

        Review findReview = reviewRepository.findByReviewId(reviewId);

        if(findReview == null){
            LOGGER.info("Review not found");
            return null;
        }

        Review review = findReview;
        VoteDTO voteDto = new VoteDTO(review.getUpVote().size(), review.getDownVote().size(),review.getReviewId());
                
        return voteDto;
    }

    @Override
    public Vote createUpVote(VoteReviewDTO upvote) {
        Vote vote = new Vote(upvote.getVote(), upvote.getUserID());

        try{

            System.out.println(upvote.getReviewID());
            Review review = reviewRepository.findByReviewId(upvote.getReviewID());

            if(review == null){

                throw new UnsupportedOperationException("Review not found");
            }

            Review reviewUpdate = review;
            
            reviewUpdate.addUpVote(vote);

            reviewRepository.save(reviewUpdate);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Vote createDownVote(VoteReviewDTO downvote) {
        Vote vote = new Vote(downvote.getVote(), downvote.getUserID());

        try{
            Review review = reviewRepository.findByReviewId(downvote.getReviewID());

            if(review == null){
                return null;
            }

            Review reviewUpdate = review;
            
            reviewUpdate.addDownVote(vote);
            reviewRepository.save(reviewUpdate);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        throw new UnsupportedOperationException("Unimplemented method 'createDownVote'");
    }
}
