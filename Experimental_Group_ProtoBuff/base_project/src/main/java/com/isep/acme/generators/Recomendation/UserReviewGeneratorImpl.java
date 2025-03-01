package com.isep.acme.generators.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import com.isep.acme.repositories.ReviewServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class UserReviewGeneratorImpl implements ReviewRecomendationGenerator {
    @Override
    public ReviewDTOOuterClass.ReviewCatalog generateReviewRecomendations(Long userId, Optional<List<User>> usersOptionalList, Optional<List<Review>> reviewsOptionalList) {
        Optional<List<Review>> r = reviewsOptionalList;
        List<ReviewDTOOuterClass.ReviewDTO> reviews = new ArrayList<>();
        if(r.isEmpty()) {
            return ReviewDTOOuterClass.ReviewCatalog.newBuilder().build();
        }
        for (Review review : r.get()) {
            if (review.getUser().getUserId() != userId && review.getUpVote().size() > 4
                    && review.getUpVote().size() > (review.getUpVote().size() + review.getDownVote().size()) * 0.6) {
                reviews.add(review.toDTO());
            }
        }
        return ReviewDTOOuterClass.ReviewCatalog.newBuilder().addAllReviews(reviews).build();
    }
}