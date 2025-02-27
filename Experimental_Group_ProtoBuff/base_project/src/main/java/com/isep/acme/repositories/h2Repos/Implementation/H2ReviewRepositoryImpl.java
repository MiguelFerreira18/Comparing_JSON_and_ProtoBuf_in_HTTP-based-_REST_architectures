package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Mapper.ReviewMapper;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import com.isep.acme.repositories.ReviewServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class H2ReviewRepositoryImpl implements ReviewServiceRepo {

    @Autowired
    private ReviewRepository repository;

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    @Override
    public Optional<List<Review>> findByProductId(Product product) {
        return repository.findByProductId(product);
    }

    @Override
    public Optional<List<ReviewDTO>> findPendingReviews() {
        List<ReviewDTO> reviews = new ArrayList<>();
        repository.findPendingReviews().get().forEach(review -> {
            reviews.add(ReviewMapper.toDto(review));
        });
        return Optional.of(reviews);
    }

    @Override
    public Optional<List<Review>> findActiveReviews() {
        List<Review> reviews = repository.findActiveReviews().get();
        return repository.findActiveReviews();
    }

    @Override
    public Optional<List<ReviewDTO>> findByProductIdStatus(Product product, String status) {
        List<ReviewDTO> reviews = new ArrayList<>();
        repository.findByProductIdStatus(product, status).get().forEach(review -> {
            reviews.add(ReviewMapper.toDto(review));
        });
        return Optional.of(reviews);
    }

    @Override
    public Optional<List<ReviewDTO>> findByUserId(User user) {
        List<ReviewDTO> reviews = new ArrayList<>();
        repository.findByUserId(user).get().forEach(review -> {
            reviews.add(ReviewMapper.toDto(review));
        });
        return Optional.of(reviews);
    }

    @Transactional
    @Override
    public Optional<Review> findById(Long reviewId) {
        return repository.findById(reviewId);
    }

    @Override
    public Optional<List<Review>> findTopReviews() {
        return repository.findTopReviews();
    }

    @Transactional
    @Override
    public ReviewDTOOuterClass.ReviewCatalog findAll() {
        Iterable<Review> reviews = repository.findAll();
        List<ReviewDTOOuterClass.ReviewDTO> reviewsDTO = new ArrayList<>();
        for (Review r : reviews) {
            reviewsDTO.add(r.toDTO());
        }

        return ReviewDTOOuterClass.ReviewCatalog.newBuilder().addAllReviews(reviewsDTO).build();
    }
}
