package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.model.H2Entity.Rating;
import com.isep.acme.repositories.RatingServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class H2RatingRepositoryImpl implements RatingServiceRepo {

    @Autowired
    private RatingRepository repository;

    @Override
    public Optional<Rating> findByRate(Double rate) {
        return repository.findByRate(rate);
    }
}
