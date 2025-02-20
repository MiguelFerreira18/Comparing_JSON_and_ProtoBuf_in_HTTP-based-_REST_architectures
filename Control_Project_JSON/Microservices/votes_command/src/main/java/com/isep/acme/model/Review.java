package com.isep.acme.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
public class Review {

    @Id
    private Long idReview;

    @Version
    private long version;

    @Column(nullable = true)
    private String approvalStatus;

    @Column(nullable = true)
    private String reviewText;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Vote> upVote;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Vote> downVote;

    @Column(nullable = true)
    private String report;

    @Column(nullable = true)
    private LocalDate publishingDate;

    @Column(nullable = true)
    private String funFact;

    /*
    @Column(nullable = false)
    private Long productId;
    */

    @Column(nullable = true)
    private double rating;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    */

    protected Review(){}

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText, final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version = Objects.requireNonNull(version);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final  String reviewText, final List<Vote> upVote, final List<Vote> downVote, final String report, final LocalDate publishingDate, final String funFact,double rating) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setRating(rating);
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final  String reviewText, final String report, final LocalDate publishingDate, final String funFact, double rating) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

        setReport(report);
        setRating(rating);
    }

    public Review(final String reviewText, LocalDate publishingDate, String funFact, double rating) {
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        this.upVote = new ArrayList<>();
        this.downVote = new ArrayList<>();
    }

    public Review(Long reviewId, String reviewText, LocalDate publishingDate, String approvalStatus, String funFact, Double rating){
        this.idReview = reviewId;
        this.reviewText = reviewText;
        this.publishingDate = publishingDate;
        this.approvalStatus = approvalStatus;
        this.funFact = funFact;
        this.rating = rating;
        this.upVote = new ArrayList<Vote>();
        this.downVote = new ArrayList<Vote>();
    }

    public boolean addUpVote(Vote upVote) {

        if(!this.upVote.contains(upVote)){
            this.upVote.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(Vote downVote) {

        if(!this.downVote.contains(downVote)){
            this.downVote.add(downVote);
            return true;
        }
        return false;
    }
}
