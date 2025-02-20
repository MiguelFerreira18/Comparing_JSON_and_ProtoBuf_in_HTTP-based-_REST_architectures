package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.AggregatedRating;
import com.isep.acme.model.H2Entity.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("h2Profile")
@Repository
public interface AggregatedRatingRepository extends CrudRepository<AggregatedRating, Long> {

    @Query("SELECT a FROM AggregatedRating a WHERE a.product=:product")
    Optional<AggregatedRating> findByProductId(Product product);

}
