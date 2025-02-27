package com.isep.acme.services;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.generators.Recomendation.ReviewRecomendationGenerator;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import com.isep.acme.repositories.ReviewServiceRepo;
import com.isep.acme.repositories.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewRecomendationServiceI implements ReviewRecomendationService {
    @Autowired
    private ReviewRecomendationGenerator reviewService;
    @Autowired
    private UserServiceRepo userServiceRepo;
    @Autowired
    private ReviewServiceRepo reviewServiceRepo;

    @Transactional
    public ReviewDTOOuterClass.ReviewCatalog getRecomendations(Long userId) {
        List<User> users = userServiceRepo.getAllUser();
        Optional<List<Review>> r = reviewServiceRepo.findActiveReviews();

        return reviewService.generateReviewRecomendations(userId, Optional.of(users), r);
    }
}
