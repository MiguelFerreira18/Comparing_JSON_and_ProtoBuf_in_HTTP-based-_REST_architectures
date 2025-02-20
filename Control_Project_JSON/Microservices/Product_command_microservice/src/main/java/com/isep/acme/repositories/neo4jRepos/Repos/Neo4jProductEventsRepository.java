package com.isep.acme.repositories.neo4jRepos.Repos;

import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;
import com.isep.acme.model.Neo4jEntity.Neo4jProductAcceptanceEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Neo4jProductEventsRepository extends Neo4jRepository<Neo4jProductAcceptanceEvent,Long> {

    @Query("MATCH (p:Neo4jProductAcceptanceEvent) WHERE p.product.sku = $sku")
    public List<ProductAcceptanceEvent> findAllFromProductSku(String sku);

    @Query("MATCH (p:Neo4jProductAcceptanceEvent) WHERE p.product.productID = $productID RETURN COUNT(p)")
    public int countProductApprovals(Long productID);

}
