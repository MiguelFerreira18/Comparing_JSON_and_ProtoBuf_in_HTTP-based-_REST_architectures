package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.model.H2Entity.AggregatedRating;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.repositories.AggregatedRatingServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.AggregatedRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class H2AggregatedRatingRepoImpl implements AggregatedRatingServiceRepo {

    @Autowired
    private AggregatedRatingRepository repository;

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        return repository.findByProductId(product);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        return repository.save(aggregatedRating);
    }
}

