package com.isep.acme.controllers;

import com.isep.acme.model.H2Entity.AggregatedRating;
import com.isep.acme.services.AggregatedRatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AggregatedRating", description = "Endpoints for managing aggregated Rating")
@RestController
@RequestMapping("/aggregatedrating")
public class AggregatedRatingController {

    @Autowired
    private AggregatedRatingService aService;


    @GetMapping(value = "/{sku}")
    ResponseEntity<AggregatedRating> getAverage(@PathVariable("sku") final String sku) {
        AggregatedRating a = aService.save(sku);

        return ResponseEntity.ok().body(a);
    }
}
