package com.isep.acme.Dto;

import com.isep.acme.model.H2Entity.Product;


public class AggregatedRatingDto {

    private Long aggregatedId;
    private double average;
    private Product product;


    public AggregatedRatingDto(double average, Product product) {
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }

}
