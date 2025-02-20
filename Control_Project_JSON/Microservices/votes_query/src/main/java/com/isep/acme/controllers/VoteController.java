package com.isep.acme.controllers;

import com.isep.acme.services.IVoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isep.acme.dto.VoteDTO;
import com.isep.acme.dto.VoteReviewDTO;

@Tag(name = "Votes", description = "Endpoints for managing votes of reviews")
@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private IVoteService voteService;

    //@Autowired
    //public VoteController(IVoteService voteService) {
    //    this.voteService = voteService;
    //}

    @GetMapping("/{reviewId}")
    @Operation(summary = "Get upvotes of a review")
    public ResponseEntity<VoteDTO> getVotesByReview(@PathVariable("reviewId") Long reviewId) {

        VoteDTO voteDto = voteService.getVotesByReviewId(reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(voteDto);
    }

    @GetMapping("/{reviewId}/downvotes")
    @Operation(summary = "Get downvotes of a review")
    public ResponseEntity<VoteDTO> downvotes(@PathVariable("reviewId") Long reviewId) {

        VoteDTO voteDto = voteService.getVotesByReviewId(reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(voteDto);
    }

    @GetMapping("/{reviewId}/upvotes")
    @Operation(summary = "Get upvotes of a review")
    public ResponseEntity<VoteDTO> upvotes(@PathVariable("reviewId") Long reviewId) {

        VoteDTO voteDto = voteService.getVotesByReviewId(reviewId);
        
        return ResponseEntity.status(HttpStatus.OK).body(voteDto);
    }
}
