package com.isep.acme.repositories.neo4jRepos.Repos;

import com.isep.acme.model.Neo4jEntity.Neo4jProduct;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface Neo4jProductRepository extends Neo4jRepository<Neo4jProduct, Long> {

    @Query("MATCH (p:Neo4jProduct) WHERE p.designation = $designation RETURN p")
    List<Neo4jProduct> findByDesignation(String designation);

    @Query("MATCH (p:Neo4jProduct) WHERE p.sku = $sku RETURN p LIMIT 1")
    Optional<Neo4jProduct> findBySku(String sku);

    // Obtain the catalog of products: Show SKU and designation of all products
    @Query("MATCH (p:Neo4jProduct) RETURN p")
    List<Neo4jProduct> getCatalog();

    // Delete the product given the SKU
    @Query("MATCH (p:Neo4jProduct) WHERE p.sku = $sku DETACH DELETE p")
    void deleteBySku(String sku);

    // Update the product given the SKU
    @Query("MATCH (p:Neo4jProduct) WHERE p.sku = $sku SET p.sku = $newSku")
    Neo4jProduct updateBySku(String sku, String newSku);

    @Query("MATCH (r:Neo4jReview)-[:BELONGS_TO]->(p:Neo4jProduct) WHERE toLower(r.approvalStatus) = toLower($status) AND id(r) = $reviewId RETURN p LIMIT 1")
    Optional<Neo4jProduct> getProductFromReview(@Param("reviewId" ) Long reviewId, @Param("status") String status);


}
