package com.isep.acme.repositories;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;

import java.util.List;
import java.util.Optional;

public interface ReviewServiceRepo {

    Review save(Review review);
    void delete(Review review);


    Optional<List<Review>> findByProductId(Product product);

    Optional<List<ReviewDTO>> findPendingReviews();

    Optional<List<Review>> findActiveReviews();

    Optional<List<ReviewDTO>> findByProductIdStatus(Product product, String status);

    Optional<List<ReviewDTO>> findByUserId(User user);

    Optional<Review> findById(Long reviewId);


    Optional<List<Review>> findTopReviews();

    List<ReviewDTO> findAll();


}
