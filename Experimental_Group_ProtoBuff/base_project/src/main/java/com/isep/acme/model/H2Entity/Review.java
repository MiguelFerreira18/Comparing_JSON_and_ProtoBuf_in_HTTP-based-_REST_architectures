package com.isep.acme.model.H2Entity;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;

    @Version
    private long version;

    @Column(nullable = false)
    private String approvalStatus;

    @Column(nullable = false)
    private String reviewText;

    @ElementCollection
    @Column(nullable = true)
    private List<Vote> upVote;

    @ElementCollection
    @Column(nullable = true)
    private List<Vote> downVote;

    @Column(nullable = true)
    private String report;

    @Column(nullable = false)
    private LocalDate publishingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH}, fetch = FetchType.LAZY, optional = true)
    private Rating rating;

    protected Review() {
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText, final LocalDate publishingDate) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version = Objects.requireNonNull(version);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText, final List<Vote> upVote, final List<Vote> downVote, final String report, final LocalDate publishingDate,  Product product, Rating rating, User user) {
        this(idReview, version, approvalStatus, reviewText, publishingDate);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);

    }

    public Review(final String reviewText, LocalDate publishingDate, Product product, Rating rating, User user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setRating(rating);
        setUser(user);
        this.upVote = new ArrayList<>();
        this.downVote = new ArrayList<>();
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public Long getIdReview() {
        return idReview;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public Boolean setApprovalStatus(String approvalStatus) {
    if(approvalStatus != null) {
        if (approvalStatus.equalsIgnoreCase("pending") ||
                approvalStatus.equalsIgnoreCase("approved") ||
                approvalStatus.equalsIgnoreCase("rejected")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }
    return false;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        if (reviewText == null || reviewText.isBlank()) {
            throw new IllegalArgumentException("Review Text is a mandatory attribute of Review.");
        }
        if (reviewText.length() > 2048) {
            throw new IllegalArgumentException("Review Text must not be greater than 2048 characters.");
        }

        this.reviewText = reviewText;
    }

    public void setReport(String report) {
        if (report.length() > 2048) {
            throw new IllegalArgumentException("Report must not be greater than 2048 characters.");
        }
        this.report = report;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public long getVersion() {
        return version;
    }



    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReport() {
        return report;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rating getRating() {
        if (rating == null) {
            return new Rating(0.0);
        }
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Vote> getUpVote() {
        return upVote;
    }

    public void setUpVote(List<Vote> upVote) {
        this.upVote = upVote;
    }

    public List<Vote> getDownVote() {
        return downVote;
    }

    public void setDownVote(List<Vote> downVote) {
        this.downVote = downVote;
    }

    public boolean addUpVote(Vote upVote) {

        if (!this.approvalStatus.equalsIgnoreCase("approved"))
            return false;

            this.upVote.add(upVote);
            return true;
    }

    public boolean addDownVote(Vote downVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.downVote.contains(downVote)) {
            this.downVote.add(downVote);
            return true;
        }
        return false;
    }

    public ReviewDTOOuterClass.ReviewDTO toDTO() {
        return ReviewDTOOuterClass.ReviewDTO.newBuilder()
                .setIdReview(idReview)
                .setReviewText(reviewText)
                .setPublishingDate(toProto(publishingDate))
                .setApprovalStatus(approvalStatus)
                .setRating(getRating().getRate())
                .setVote(getDownVote().size() + getUpVote().size())
                .setProduct(product.toDto())
                .build();
    }
    private ReviewDTOOuterClass.LocalDate toProto(LocalDate date) {
        return ReviewDTOOuterClass.LocalDate.newBuilder()
                .setDay(date.getDayOfMonth())
                .setMonth(date.getMonthValue())
                .setYear(date.getYear())
                .build();
    }
}
