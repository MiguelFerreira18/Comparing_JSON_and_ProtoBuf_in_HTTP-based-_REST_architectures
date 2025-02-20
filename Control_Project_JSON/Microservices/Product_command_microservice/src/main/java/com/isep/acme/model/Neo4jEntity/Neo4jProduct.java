package com.isep.acme.model.Neo4jEntity;

import com.isep.acme.Dto.ProductDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Profile("neo4jProfile")
@Node
public class Neo4jProduct {
    @Id
    @GeneratedValue
    private Long productID;

    @Property
    private String sku;

    @Property
    private String designation;

    @Property
    private String description;
    @Property
    private boolean isPublished;

    public Neo4jProduct() {
    }

    public Neo4jProduct(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }

    public Neo4jProduct(final Long productID, final String sku, final String designation, final String description, boolean isPublished) {
        this(productID, sku);
        setDescription(description);
        setDesignation(designation);
        setPublished(isPublished);
    }

    public Neo4jProduct(final String sku) {
        setSku(sku);
    }

    public Neo4jProduct(final String sku, final String designation, final String description) {
        this(sku);
        setDescription(description);
        setDesignation(designation);
        this.isPublished = false;
    }

    public void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
        }
        if (sku.length() < 8 || sku.length() > 12) {
            throw new IllegalArgumentException("SKU must be 12 characters long.");
        }

        this.sku = sku;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation is a mandatory attribute of Product.");
        }
        if (designation.length() > 50) {
            throw new IllegalArgumentException("Designation must not be greater than 50 characters.");
        }
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is a mandatory attribute of Product.");
        }

        if (description.length() > 1200) {
            throw new IllegalArgumentException("Description must not be greater than 1200 characters.");
        }

        this.description = description;
    }

    public String getSku() {
        return sku;
    }


    public void updateProduct(Neo4jProduct p) {
        setDesignation(p.getDesignation());
        setDescription(p.getDescription());
    }

    public Long getProductID() {
        return productID;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }

}
