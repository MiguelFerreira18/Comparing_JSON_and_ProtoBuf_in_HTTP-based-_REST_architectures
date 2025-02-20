package com.isep.acme.services;

import com.isep.acme.model.H2Entity.AggregatedRating;

public interface AggregatedRatingService {

    AggregatedRating save(String sku);
}
