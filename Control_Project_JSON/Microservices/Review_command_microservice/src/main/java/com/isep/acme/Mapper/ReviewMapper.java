package com.isep.acme.Mapper;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;


public class ReviewMapper {

    public static ReviewDTO toDto(Review review) {
        return new ReviewDTO(review.getIdReview(), review.getReviewText(), review.getPublishingDate(), review.getApprovalStatus(), review.getFunFact(), review.getRating().getRate(), review.getUpVote().size(), review.getNumberApproved());
    }

    public static List<ReviewDTO> toDtoList(List<Review> review) {
        List<ReviewDTO> dtoList = new ArrayList<>();

        for (Review rev : review) {
            dtoList.add(toDto(rev));
        }
        return dtoList;
    }
}
