package com.isep.acme.bootstrapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.dto.ReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.RestService;

@Component
public class bootstrapper implements ApplicationRunner {
    
    @Autowired
    RestService restService;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void run(ApplicationArguments    args) throws Exception {

        bootstrapIfFails();
        // fetchDataFromMicroservice();
    }

    private void fetchDataFromMicroservice() {

        List<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();

        try{
            reviewDTOs = restService.fetchDataFromMicroservice();
        }
        catch(Exception e){
            System.out.println("Error fetching data from microservice" + e.getMessage());
            bootstrapIfFails();
            return;
        }

        for (ReviewDTO reviewDTO : reviewDTOs) {
            Optional<Review> review = reviewRepository.findById(reviewDTO.getIdReview());

            if(review.isPresent()){
                continue;
            }

            Review reviewToSave = new Review(
                reviewDTO.getIdReview(),
                reviewDTO.getReviewText(),
                reviewDTO.getPublishingDate(),
                reviewDTO.getApprovalStatus(),
                reviewDTO.getFunFact(),
                reviewDTO.getRating()
            );

            reviewRepository.save(reviewToSave);
        }
    }

    private void createReview(Long reviewId, String reviewText, LocalDate publishingDate, String approvalStatus, String funFact, Double rating){
        
        if (reviewRepository.findById(reviewId).isEmpty()) {
            
            Review reviewToSave = new Review(
                reviewId,
                reviewText,
                publishingDate,
                approvalStatus,
                funFact,
                rating
            );

            reviewRepository.save(reviewToSave);
        }
    }
    private void bootstrapIfFails(){

        createReview(1L, "Review text", LocalDate.now(), "Approved", "Not that fun", 0.5);
        createReview(2L, "Review text", LocalDate.now(), "Approved", "Not that fun", 0.5);
        createReview(3L, "Review text", LocalDate.now(), "Approved", "Not that fun", 0.5);
        createReview(4L, "Updated review with new insightst", LocalDate.now(), "Approved", "Surprising fact", 2.0);
        createReview(5L, "fifth review for testing", LocalDate.now(), "Pending", "Interesting detail", 2.5);
    }
}
