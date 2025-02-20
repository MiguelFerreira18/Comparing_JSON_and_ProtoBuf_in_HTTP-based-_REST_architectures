package com.isep.acme.repositories.MongoRepos.Repos;

import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;

import com.isep.acme.model.MongoDbEntity.MongoProductAcceptanceEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoDbProductEventsRepository extends MongoRepository<MongoProductAcceptanceEvent, Long> {
    @Query("{'sku' :  ?0 }")
    public List<ProductAcceptanceEvent> findAllFromProductSku(String sku);

}
