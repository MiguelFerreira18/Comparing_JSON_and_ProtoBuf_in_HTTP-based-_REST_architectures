package com.isep.acme.generators.Sku.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ReviewRecomendationGenerator {
    public List<ReviewDTO> generateReviewRecomendations(Long userId, Optional<List<User>> users, Optional<List<Review>> reviewsList);
}
