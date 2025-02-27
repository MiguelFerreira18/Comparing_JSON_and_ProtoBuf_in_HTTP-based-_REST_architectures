package com.isep.acme.generators.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ReviewRecomendationGenerator {
    public ReviewDTOOuterClass.ReviewCatalog generateReviewRecomendations(Long userId, Optional<List<User>> users, Optional<List<Review>> reviewsList);
}
