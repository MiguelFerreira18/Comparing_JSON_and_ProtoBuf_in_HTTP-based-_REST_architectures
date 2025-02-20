package com.isep.acme.controllers;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.services.ReviewRecomendationService;
import com.isep.acme.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    ReviewRecomendationService reviewRecomendationServiceI;
    @Autowired
    private ReviewService rService;

    @GetMapping("/get/{userId}")
    public List<ReviewDTO> getRecommendation(@PathVariable final Long userId) {
        return reviewRecomendationServiceI.getRecomendations(userId);
    }

    @PostMapping("/get/{userId}/{reviewId}")
    public ResponseEntity<ReviewDTO> publishReview(@PathVariable(value = "userId") final Long userID,
                                                   @PathVariable(value = "reviewId") final Long reviewID) {

        ReviewDTO r = rService.publishReview(userID, reviewID);

        return ResponseEntity.ok().body(r);
    }

}
