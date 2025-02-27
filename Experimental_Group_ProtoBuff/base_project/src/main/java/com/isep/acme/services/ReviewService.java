package com.isep.acme.services;

import com.isep.acme.Dto.CreateReviewDTO;
import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Dto.VoteReviewDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.protobuf.CreateReviewDTOOuterClass;
import com.isep.acme.protobuf.ReviewDTOOuterClass;

import java.util.List;

public interface ReviewService {

    ReviewDTOOuterClass.ReviewCatalog getAll();

    List<ReviewDTO> getReviewsOfProduct(String sku, String status);

    ReviewDTOOuterClass.ReviewDTO create(CreateReviewDTOOuterClass.CreateReviewDTO createReviewDTO, String sku);

    boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO);

    Double getWeightedAverage(Product product);

    Boolean DeleteReview(Long reviewId);

    List<ReviewDTO> findPendingReview();

    ReviewDTO moderateReview(Long reviewID, String approved);

    List<ReviewDTO> findReviewsByUser(Long userID);
}
