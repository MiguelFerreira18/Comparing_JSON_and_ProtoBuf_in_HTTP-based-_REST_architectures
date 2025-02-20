package com.isep.acme.model.H2Entity;

import com.isep.acme.Dto.ProductDTO;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;

    @Column(nullable = false, unique = true)
    public String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean isPublished;

    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    public Product() {
    }

    public Product(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }
    public Product(final Long productID, final String sku, final String designation, final String description, boolean isPublished) {
        this(productID, sku);
        setDescription(description);
        setDesignation(designation);
        setPublished(isPublished);
    }

    //INITIALIZE NEW PRODUCT
    public Product(final String sku, final String designation, final String description) {
        setSku(sku);
        setDescription(description);
        setDesignation(designation);
        this.isPublished = false;
    }

    //INITIALIZE IN THE BOOTSTRAPPER
    public Product(final String sku, final String designation, final String description,boolean isPublished) {
        setSku(sku);
        setDescription(description);
        setDesignation(designation);
        this.isPublished = isPublished;
    }

    public void setSku(String sku) {
//        if (sku == null || sku.isBlank()) {
//            throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
//        }
//        if (sku.length() < 8 || sku.length() > 12) {
//            throw new IllegalArgumentException("SKU must be 12 characters long.");
//        }

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


    public void updateProduct(Product p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public Long getProductID() {
        return productID;
    }

    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }


    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productID=").append(productID);
        sb.append(", sku='").append(sku).append('\'');
        sb.append(", designation='").append(designation).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isPublished=").append(isPublished);
        sb.append('}');
        return sb.toString();
    }
}
