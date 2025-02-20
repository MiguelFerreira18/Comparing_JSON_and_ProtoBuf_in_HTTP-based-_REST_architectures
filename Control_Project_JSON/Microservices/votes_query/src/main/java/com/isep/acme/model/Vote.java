package com.isep.acme.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;

@Node("Vote")
@Data
public class Vote {
    
    @Id
    @GeneratedValue
    private Long idVote;
    private String vote;
    private Long userID;

    public Vote() {
    }

    public Vote(String vote, Long userID) {
        this.vote = vote;
        this.userID = userID;
    }
}
