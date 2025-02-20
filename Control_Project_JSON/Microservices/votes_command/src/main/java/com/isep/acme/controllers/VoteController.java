package com.isep.acme.controllers;

import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.producer.DownVoteProducer;
import com.isep.acme.producer.UpVoteProducer;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.IVoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Votes", description = "Endpoints for managing votes of reviews")
@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private final IVoteService voteService;

    @Autowired
    private final UpVoteProducer upVoteProducer;

    @Autowired
    private final DownVoteProducer downVoteProducer;

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    public VoteController(IVoteService voteService, UpVoteProducer upVoteProducer,DownVoteProducer downVoteProducer, ReviewRepository reviewRepository) {
        this.voteService = voteService;
        this.upVoteProducer = upVoteProducer;
        this.downVoteProducer = downVoteProducer;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/upvote/{reviewId}")
    @Operation(summary = "add upvote")
    public ResponseEntity<?> upvoteReview(@PathVariable("reviewId") Long reviewId, @RequestBody Long userId) {
        
        VoteReviewDTO vote = voteService.upvoteReview(reviewId, userId);

        if(vote == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Review not found");
        }

        //Send voteReviewDTO to RabbitMQ
        upVoteProducer.sendUpvotedMessage(vote);

        return ResponseEntity.status(HttpStatus.OK).body(vote);
    }

    @PostMapping("/downvote/{reviewId}")
    @Operation(summary = "add downVote")
    public ResponseEntity<VoteReviewDTO> downvoteReview(@PathVariable("reviewId") Long reviewId, @RequestBody Long userId) {
        VoteReviewDTO vote = voteService.downvoteReview(reviewId, userId);

        //Send voteReviewDTO to RabbitMQ
        downVoteProducer.sendDownvotedMessage(vote);
        
        return ResponseEntity.status(HttpStatus.OK).body(vote);
    }
}
