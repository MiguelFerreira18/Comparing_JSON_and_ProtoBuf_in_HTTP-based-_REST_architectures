package com.isep.acme.model.Neo4jEntity;

import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;

@Profile("neo4jProfile")
@Node
public class Neo4jProductAcceptanceEvent {
    @Id
    @GeneratedValue
    private Long id;
    @Relationship(type = "HAS_EVENT", direction = Relationship.Direction.OUTGOING)
    private Neo4jProduct product;
    @Relationship(type = "FROM", direction = Relationship.Direction.OUTGOING)
    private Neo4jUser user;

    public Neo4jProductAcceptanceEvent() {
    }

    public Neo4jProductAcceptanceEvent(Long id, Neo4jProduct product, Neo4jUser user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public Neo4jProductAcceptanceEvent(Neo4jProduct product, Neo4jUser user) {
        this.product = product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Neo4jProduct getProduct() {
        return product;
    }

    public void setProduct(Neo4jProduct product) {
        this.product = product;
    }

    public Neo4jUser getUser() {
        return user;
    }

    public void setUser(Neo4jUser user) {
        this.user = user;
    }

}
