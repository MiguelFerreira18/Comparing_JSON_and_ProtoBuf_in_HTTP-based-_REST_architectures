package com.isep.acme.repositories;

import com.isep.acme.model.H2Entity.Rating;

import java.util.Optional;

public interface RatingServiceRepo {
    Optional<Rating> findByRate(Double rate);

}
