package com.isep.acme.model.H2Entity;

import javax.persistence.*;

@Entity
public class PublishVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PublishId;

    @Column(nullable = false)

    private Long UserId;

    @Column(nullable = false)

    private Long ReviewId;

    public PublishVote(Long userId, Long reviewId) {
        UserId = userId;
        ReviewId = reviewId;
    }

    protected PublishVote() {

    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getReviewId() {
        return ReviewId;
    }

    public void setReviewId(Long reviewId) {
        ReviewId = reviewId;
    }
}
