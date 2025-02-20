package com.isep.acme.services;

import com.isep.acme.Dto.CreateReviewDTO;
import com.isep.acme.Dto.CreateReviewVoteDTO;
import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Dto.VoteReviewDTO;
import com.isep.acme.model.H2Entity.Product;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getAll();

    List<ReviewDTO> getReviewsOfProduct(String sku, String status);

    ReviewDTO create(CreateReviewDTO createReviewDTO, String sku);

    boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO);

    Double getWeightedAverage(Product product);

    Boolean DeleteReview(Long reviewId);

    List<ReviewDTO> findPendingReview();

    List<ReviewDTO> findPublishedReview();

    ReviewDTO moderateReview(Long reviewID, String approved);

    List<ReviewDTO> findReviewsByUser(Long userID);
    Integer getSenderID();

    ReviewDTO publishReview(Long userID,Long reviewID);

}
