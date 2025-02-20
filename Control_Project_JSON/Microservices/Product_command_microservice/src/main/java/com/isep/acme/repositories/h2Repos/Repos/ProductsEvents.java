package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("h2Profile")
@Repository
public interface ProductsEvents extends CrudRepository<ProductAcceptanceEvent, Long> {

    @Query("SELECT p FROM ProductAcceptanceEvent p WHERE p.product.sku =:sku")
    public List<ProductAcceptanceEvent> findAllFromProductSku(String sku);

    @Query("SELECT COUNT(p) FROM ProductAcceptanceEvent p WHERE p.product.productID =:productID")
    public int countProductApprovals(Long productID);
}
