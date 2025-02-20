package com.isep.acme.controllers;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.services.ReviewRecomendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    ReviewRecomendationService reviewRecomendationServiceI;

    @GetMapping("/get/{userId}")
    public List<ReviewDTO> getRecommendation(@PathVariable final Long userId) {
        return reviewRecomendationServiceI.getRecomendations(userId);
    }
}
