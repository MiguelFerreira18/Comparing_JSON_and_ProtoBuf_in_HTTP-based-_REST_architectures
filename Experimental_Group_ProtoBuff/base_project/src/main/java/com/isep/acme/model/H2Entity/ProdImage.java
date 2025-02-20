package com.isep.acme.model.H2Entity;

import com.isep.acme.Dto.ImageDTO;

import javax.annotation.Resource;
import javax.persistence.*;


@Entity
public class ProdImage {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Lob
    private Resource image;


    public ProdImage(Product product, Resource image) {
        setProduct(product);

    }


    public ProdImage() {

    }

    private void setProduct(Product product) {
    }


    public ImageDTO toDto() {
        return new ImageDTO(this.id, product.getProductID());
    }

}

