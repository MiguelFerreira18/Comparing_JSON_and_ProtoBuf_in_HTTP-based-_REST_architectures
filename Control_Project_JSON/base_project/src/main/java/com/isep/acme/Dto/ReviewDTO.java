package com.isep.acme.Dto;

import com.isep.acme.model.H2Entity.Product;

import java.time.LocalDate;

public class ReviewDTO {

    private Long idReview;
    private String reviewText;
    private LocalDate publishingDate;
    private String approvalStatus;
    private Double rating;
    private Integer vote;
    private ProductDTO product;

    public ReviewDTO(Long idReview, String reviewText, LocalDate publishingDate, String approvalStatus, Double rating, Integer vote, ProductDTO product) {
        this.idReview = idReview;
        this.reviewText = reviewText;
        this.publishingDate = publishingDate;
        this.approvalStatus = approvalStatus;
        this.rating = rating;
        this.vote = vote;
        this.product = product;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public Long getIdReview() {
        return this.idReview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
