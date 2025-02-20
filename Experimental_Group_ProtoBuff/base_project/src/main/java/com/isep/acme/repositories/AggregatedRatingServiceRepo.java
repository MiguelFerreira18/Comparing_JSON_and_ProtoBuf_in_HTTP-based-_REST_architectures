package com.isep.acme.repositories;

import com.isep.acme.model.H2Entity.AggregatedRating;
import com.isep.acme.model.H2Entity.Product;

import java.util.Optional;

public interface AggregatedRatingServiceRepo {
    Optional<AggregatedRating> findByProductId(Product product);
    AggregatedRating save(AggregatedRating aggregatedRating);
}
