package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Profile("h2Profile")
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    @Query("SELECT p FROM Product p")
    List<Product> getCatalog();

    //Obtain the details of products -> Destails: show sku, designation and description of all products

    //Delete the product when given the SKU
    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.sku=:sku")
    void deleteBySku(@Param("sku") String sku);

    //Update the product when given the SKU
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.sku = :sku WHERE p.sku=:skuToChange")
    int updateBySku(@Param("sku") String sku,@Param("skuToChange") String skuToChange);

    @Query("SELECT p FROM Product p WHERE p.productID=:productID")
    Optional<Product> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}

