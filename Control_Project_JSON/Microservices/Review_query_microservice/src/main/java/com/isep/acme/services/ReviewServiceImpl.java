package com.isep.acme.services;

import com.isep.acme.Dto.CreateReviewDTO;
import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Dto.VoteReviewDTO;
import com.isep.acme.Mapper.ReviewMapper;
import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.H2Entity.*;
import com.isep.acme.repositories.ProductServiceRepo;
import com.isep.acme.repositories.ReviewServiceRepo;
import com.isep.acme.repositories.UserServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.PublishingsRepository;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewServiceRepo repository;

    @Autowired
    ReviewRepository reviewRepo;

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

    @Autowired
    ReviewRecomendationService reviewRecomendationServiceI;

    @Autowired
    PublishingsRepository publishRepo;


    private Integer senderID = (int) (Math.random() * 1000) + (int) (Math.random() * 1000);

    @Override
    public List<ReviewDTO> getAll() {
        return repository.findAll();
    }

    @Override
    public ReviewDTO create(final CreateReviewDTO createReviewDTO, String sku) {

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


        Review review = new Review(createReviewDTO.getReviewText(), date, product.get(), funfact, rating, user.get());
        repository.save(review);

        return ReviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDTO> getReviewsOfProduct(String sku, String status) {

        Optional<Product> product = pRepository.findBySku(sku);
        if (product.isEmpty()) return null;

        Optional<List<Review>> r = repository.findByProductIdStatus(product.get(), status);

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r.get());
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
        System.out.println("Getting average for product: " + product.getSku());
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

        Optional<List<Review>> r = repository.findPendingReviews();

        if (r.isEmpty()) {
            return null;
        }

        return ReviewMapper.toDtoList(r.get());
    }

    @Transactional
    @Override
    public List<ReviewDTO> findPublishedReview() {

        Optional<List<Review>> r = repository.findPublishedReview();

        if (r.isEmpty()) {
            return null;
        }

        return ReviewMapper.toDtoList(r.get());
    }

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


    @Transactional
    @Override
    public List<ReviewDTO> findReviewsByUser(Long userID) {

        final Optional<User> user = uRepository.getByUserId(userID);

        if (user.isEmpty()) return null;

        Optional<List<Review>> r = repository.findByUserId(user.get());

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r.get());
    }

    @Override
    public Integer getSenderID() {
        return senderID;
    }

    @Transactional
    @Override
    public ReviewDTO publishReview(Long userID, Long reviewID) {
        List<ReviewDTO> reviews = reviewRecomendationServiceI.getRecomendations(userID);

        if (reviews.isEmpty()) return null;

        Optional<Review> r = reviewRepo.findById(reviewID);

        if (r.isEmpty()) return null;

        Review review = r.get();

        PublishVote p = publishRepo.findByReviewIdAndUserId(review.getIdReview(), userID);

        if(p == null) {
            reviewRepo.updatePublish(review.getIdReview());
            publishRepo.save(new PublishVote(userID, review.getIdReview()));
        } else {
            publishRepo.delete(p);
            reviewRepo.updateUnpublish(review.getIdReview());
        }

        return ReviewMapper.toDto(review);
    }
}