package com.isep.acme.model.MongoDbEntity;

import com.isep.acme.Dto.ProductDTO;
import lombok.Getter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Profile("mongodbProfile")
@Document(collection = "Product")
public class MongoDBProduct {

    private Long productID;

    public String sku;

    private String designation;

    private String description;
    private boolean isPublished;
    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    public MongoDBProduct() {
    }

    public MongoDBProduct(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }

    public MongoDBProduct(final Long productID, final String sku, final String designation, final String description,boolean isPublished) {
        this(productID, sku);
        setDescription(description);
        setDesignation(designation);
        setPublished(isPublished);
    }

    public MongoDBProduct(final String sku) {
        setSku(sku);
    }

    public MongoDBProduct(final String sku, final String designation, final String description) {
        this(sku);
        setDescription(description);
        setDesignation(designation);
        this.isPublished = false;
    }

    public void setSku(String sku) {
        if (sku.length() < 8 || sku.length() > 12) {
            throw new IllegalArgumentException("SKU must be 12 characters long.");
        }

        this.sku = sku;
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

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is a mandatory attribute of Product.");
        }

        if (description.length() > 1200) {
            throw new IllegalArgumentException("Description must not be greater than 1200 characters.");
        }

        this.description = description;
    }


    public void updateProduct(MongoDBProduct p) {
        setDesignation(p.getDesignation());
        setDescription(p.getDescription());
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

