package com.isep.acme.model;

import org.springframework.data.neo4j.core.schema.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Node("Review")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "reviewId")
    private Long reviewId;

    @Relationship(type = "UPVOTE")
    private List<Vote> upVote;

    @Relationship(type = "DOWNVOTE")
    private List<Vote> downVote;

    private String reviewText;
    private LocalDate publishingDate;
    private String approvalStatus;
    private String funFact;
    private Double rating;

    public Review(){}

    public Review(Long reviewId){
        this.reviewId = reviewId;
        this.upVote = new ArrayList<Vote>();
        this.downVote = new ArrayList<Vote>();
    }

    public Review(Long reviewId, String reviewText, LocalDate publishingDate, String approvalStatus, String funFact, Double rating){
        this.reviewId = reviewId;
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
