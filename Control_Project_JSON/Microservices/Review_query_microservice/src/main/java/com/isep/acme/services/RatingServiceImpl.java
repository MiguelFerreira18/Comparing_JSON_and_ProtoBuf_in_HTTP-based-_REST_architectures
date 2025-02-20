package com.isep.acme.services;

import com.isep.acme.model.H2Entity.Rating;
import com.isep.acme.repositories.RatingServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingServiceRepo repository;

    public Optional<Rating> findByRate(Double rate) {
        return repository.findByRate(rate);
    }

}
