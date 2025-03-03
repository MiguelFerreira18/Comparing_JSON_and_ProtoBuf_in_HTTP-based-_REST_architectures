package com.isep.acme.services;

import com.isep.acme.Dto.CreateReviewDTO;
import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Dto.VoteReviewDTO;
import com.isep.acme.Mapper.ReviewMapper;
import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.H2Entity.*;
import com.isep.acme.protobuf.CreateReviewDTOOuterClass;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import com.isep.acme.repositories.ProductServiceRepo;
import com.isep.acme.repositories.ReviewServiceRepo;
import com.isep.acme.repositories.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewServiceRepo repository;

    @Autowired
    ProductServiceRepo pRepository;

    @Autowired
    UserServiceRepo uRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RatingService ratingService;

    @Autowired
    RestService restService;

    @Override
    public ReviewDTOOuterClass.ReviewCatalog getAll() {
        return repository.findAll();
    }

    @Override
    public ReviewDTOOuterClass.ReviewDTO create(final CreateReviewDTOOuterClass.CreateReviewDTO createReviewDTO, String sku) {

        final Optional<Product> product = pRepository.findBySku(sku);

        if (product.isEmpty()) return null;

        final var user = userServiceImpl.getUserId(createReviewDTO.getUserID());

        if (user.isEmpty()) return null;

        Rating rating = null;
        Optional<Rating> r = ratingService.findByRate(createReviewDTO.getRating());
        if (r.isPresent()) {
            rating = r.get();
        }

        LocalDate date = LocalDate.now();

        String funfact = restService.getFunFact(date);

        if (funfact == null) return null;


        Review review = new Review(createReviewDTO.getReviewText(), date, product.get(),  rating, user.get());
        repository.save(review);

        return review.toDTO();
    }

    @Override
    public List<ReviewDTO> getReviewsOfProduct(String sku, String status) {

        Optional<Product> product = pRepository.findBySku(sku);
        if (product.isEmpty()) return null;

        Optional<List<ReviewDTO>> r = repository.findByProductIdStatus(product.get(), status);

        if (r.isEmpty()) return null;

        return r.get();
    }

    @Override
    public boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO) {

        Optional<Review> review = this.repository.findById(reviewID);

        if (review.isEmpty()) return false;

        Vote vote = new Vote(voteReviewDTO.getVote(), voteReviewDTO.getUserID());
        if (voteReviewDTO.getVote().equalsIgnoreCase("upVote")) {
            boolean added = review.get().addUpVote(vote);
            if (added) {
                Review reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        } else if (voteReviewDTO.getVote().equalsIgnoreCase("downVote")) {
            boolean added = review.get().addDownVote(vote);
            if (added) {
                Review reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        }
        return false;
    }

    @Override
    public Double getWeightedAverage(Product product) {
        Optional<List<Review>> r = repository.findByProductId(product);

        if (r.isEmpty()) return 0.0;

        double sum = 0;

        for (Review rev : r.get()) {
            Rating rate = rev.getRating();

            if (rate != null) {
                sum += rate.getRate();
            }
        }

        return sum / r.get().size();
    }

    @Override
    public Boolean DeleteReview(Long reviewId) {

        Optional<Review> rev = repository.findById(reviewId);

        repository.delete(rev.get());

        if (rev.isEmpty()) {
            return null;
        }
        Review r = rev.get();

//        if (r.getUpVote().isEmpty() && r.getDownVote().isEmpty()) {
//            repository.delete(r);
//            return true;
//        }

        repository.delete(r);
        return true;
        //return false;
    }

    @Override
    public List<ReviewDTO> findPendingReview() {

        Optional<List<ReviewDTO>> r = repository.findPendingReviews();

        if (r.isEmpty()) {
            return null;
        }

        return r.get();
    }

    @Transactional
    @Override
    public ReviewDTO moderateReview(Long reviewID, String approved) throws ResourceNotFoundException, IllegalArgumentException {

        Optional<Review> r = repository.findById(reviewID);

        if (r.isEmpty()) {
            throw new ResourceNotFoundException("Review not found");
        }

        Boolean ap = r.get().setApprovalStatus(approved);

        if (!ap) {
            throw new IllegalArgumentException("Invalid status value");
        }

        Review review = repository.save(r.get());

        return ReviewMapper.toDto(review);
    }


    @Override
    public List<ReviewDTO> findReviewsByUser(Long userID) {

        final Optional<User> user = uRepository.getByUserId(userID);

        if (user.isEmpty()) return null;

        Optional<List<ReviewDTO>> r = repository.findByUserId(user.get());

        if (r.isEmpty()) return null;

        return r.get();
    }



}