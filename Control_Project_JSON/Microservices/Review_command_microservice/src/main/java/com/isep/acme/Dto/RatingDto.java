package com.isep.acme.Dto;

import javax.persistence.*;
import java.util.Objects;

public class RatingDto {


    private Long idRating;
    private long version;


    private Double rate;


    public RatingDto(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
