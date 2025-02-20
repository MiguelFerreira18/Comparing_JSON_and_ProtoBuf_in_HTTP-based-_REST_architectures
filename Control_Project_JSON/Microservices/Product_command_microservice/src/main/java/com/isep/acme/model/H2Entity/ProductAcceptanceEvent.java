package com.isep.acme.model.H2Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ProductAcceptanceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ProductAcceptanceEvent() {
    }

    public ProductAcceptanceEvent(Long id, Product product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public ProductAcceptanceEvent(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
