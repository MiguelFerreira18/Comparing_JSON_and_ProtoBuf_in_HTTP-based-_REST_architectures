package com.isep.acme.model.MongoDbEntity;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Profile("mongodbProfile")
@Document(collection = "ProductAcceptanceEvent")
public class MongoProductAcceptanceEvent {

    private Long id;
    private MongoDBProduct product;
    private MongoDBUser user;

    public MongoProductAcceptanceEvent() {
    }

    public MongoProductAcceptanceEvent(Long id, MongoDBProduct product, MongoDBUser user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public MongoProductAcceptanceEvent(MongoDBProduct product, MongoDBUser user) {
        this.product = product;
        this.user = user;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MongoDBProduct getProduct() {
        return product;
    }

    public void setProduct(MongoDBProduct product) {
        this.product = product;
    }

    public MongoDBUser getUser() {
        return user;
    }

    public void setUser(MongoDBUser user) {
        this.user = user;
    }

}
