package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Mapper.ReviewMapper;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.repositories.ReviewServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class H2ReviewRepositoryImpl implements ReviewServiceRepo {

    @Autowired
    private ReviewRepository repository;

    @Override
    public Review save(Review review) {
        return repository.save(review) ;
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
    public Optional<List<Review>> findPendingReviews() {
        return repository.findPendingReviews();
    }

    @Override
    public Optional<List<Review>> findPublishedReview() {
        return repository.findPublishedReviews();
    }

    @Override
    public Optional<List<Review>> findActiveReviews() {
        List<Review> reviews = repository.findActiveReviews().get();
        return repository.findActiveReviews();
    }

    @Override
    public Optional<List<Review>> findByProductIdStatus(Product product, String status) {
        return repository.findByProductIdStatus(product, status);
    }

    @Override
    public Optional<List<Review>> findByUserId(User user) {
        return repository.findByUserId(user);
    }

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
    public List<ReviewDTO> findAll() {
       Iterable<Review> r = repository.findAll();
        ReviewMapper mapper = new ReviewMapper();


        List<ReviewDTO> reviews = new ArrayList<>();
        for(Review review : r) {
            reviews.add(mapper.toDto(review));
        }

//        repository.findAll().iterator().forEachRemaining(reviews::add);
       return reviews;
    }
}
