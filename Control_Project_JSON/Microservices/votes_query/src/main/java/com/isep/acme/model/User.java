package com.isep.acme.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;

@Node("User")
@Data
public class User {
    
    @Id
    @GeneratedValue
    private Long idUser;

    public User() {
    }

    public User(Long idUser) {
        this.idUser = idUser;
    }
}
