package com.isep.acme.generators.Sku.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class UserReviewGeneratorImpl implements ReviewRecomendationGenerator {
    @Override
    public List<ReviewDTO> generateReviewRecomendations(Long userId, Optional<List<User>> usersOptionalList, Optional<List<Review>> reviewsOptionalList) {
        Optional<List<Review>> r = reviewsOptionalList;
        List<ReviewDTO> reviews = new ArrayList<>();
        if(r.isEmpty()) {
            return reviews;
        }
        for (Review review : r.get()) {
            if (review.getUser().getUserId() != userId && review.getUpVote().size() > 4
                    && review.getUpVote().size() > (review.getUpVote().size() + review.getDownVote().size()) * 0.6) {
                reviews.add(review.toDTO());
            }
        }
        return reviews;
    }
}