package com.isep.acme.model.H2Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idVote;
    private String vote;
    private Long userID;


    protected Vote() {

    }

    public Vote(String vote, Long userID) {
        this.vote = vote;
        this.userID = userID;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote1 = (Vote) o;
        return vote.equals(vote1.vote) && userID.equals(vote1.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vote, userID);
    }

}
