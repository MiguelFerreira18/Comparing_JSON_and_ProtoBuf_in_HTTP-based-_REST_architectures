package com.isep.acme.controllers;

import com.isep.acme.Dto.CreateReviewDTO;
import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Dto.VoteReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "Review", description = "Endpoints for managing Review")
@RestController
class ReviewController {

    @Autowired
    private ReviewService rService;
    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Operation(summary = "finds a product through its sku and shows its review by status")
    @GetMapping("/products/{sku}/reviews/{status}")
    public ResponseEntity<List<ReviewDTO>> findById(@PathVariable(value = "sku") final String sku, @PathVariable(value = "status") final String status) {

        final var review = rService.getReviewsOfProduct(sku, status);

        return ResponseEntity.ok().body(review);
    }

    @Operation(summary = "gets review by user")
    @GetMapping("/reviews/{userID}")
    public ResponseEntity<List<ReviewDTO>> findReviewByUser(@PathVariable(value = "userID") final Long userID) {

        final var review = rService.findReviewsByUser(userID);

        return ResponseEntity.ok().body(review);
    }

    @Operation(summary = "gets all reviews")
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewByUser() {

        System.out.println("get all reviews");
        return ResponseEntity.ok().body(rService.getAll());
    }

    @Operation(summary = "deletes review")
    @DeleteMapping("/reviews/{reviewID}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable(value = "reviewID") final Long reviewID) {

        Boolean rev = rService.DeleteReview(reviewID);

        if (rev == null) return ResponseEntity.notFound().build();

        if (rev == false) return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok().body(rev);
    }

    @Operation(summary = "gets pedding reviews")
    @GetMapping("/reviews/pending")
    public ResponseEntity<List<ReviewDTO>> getPendingReview() {

        List<ReviewDTO> r = rService.findPendingReview();

        return ResponseEntity.ok().body(r);
    }

    @Operation(summary = "get published reviews")
    @GetMapping("/reviews/published")
    public ResponseEntity<List<ReviewDTO>> getPublishedReview() {

        List<ReviewDTO> r = rService.findPublishedReview();

        return ResponseEntity.ok().body(r);
    }

    @GetMapping
    @RequestMapping("/rabbitmq")
    public ResponseEntity<String> getRabbitmqHost() {
        return ResponseEntity.ok().body(rabbitmqHost);
    }

}
