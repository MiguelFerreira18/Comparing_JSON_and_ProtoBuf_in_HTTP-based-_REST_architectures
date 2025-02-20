package com.isep.acme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ReviewRepository;

@Service
public class ReviewServiceImp implements IReviewService {
     
    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(VoteReviewDTO reviewDto){

        Review review = reviewRepository.findByReviewId(reviewDto.getReviewID());
        Vote vote = new Vote(reviewDto.getVote(), reviewDto.getUserID());
        User user = new User(reviewDto.getUserID());

        if(review == null){
            Review reviewToUpdate = review;

            if(reviewDto.getVote().equals("upVote")){
                reviewToUpdate.addUpVote(vote);
            }else{
                reviewToUpdate.addDownVote(vote);
            }
            return reviewRepository.save(reviewToUpdate);
        }

        Review reviewToCreate = new Review(reviewDto.getReviewID());

        if(reviewDto.getVote().equals("upVote")){
                reviewToCreate.addUpVote(vote);
            }else{
                reviewToCreate.addDownVote(vote);
            }
        
        return reviewRepository.save(reviewToCreate);
    }
}
