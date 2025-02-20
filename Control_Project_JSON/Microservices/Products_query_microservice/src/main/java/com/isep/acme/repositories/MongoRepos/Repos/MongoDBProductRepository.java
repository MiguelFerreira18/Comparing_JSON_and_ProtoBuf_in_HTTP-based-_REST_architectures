package com.isep.acme.repositories.MongoRepos.Repos;

import com.isep.acme.model.MongoDbEntity.MongoDBProduct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile("mongodbProfile")
@Repository
public interface MongoDBProductRepository extends MongoRepository<MongoDBProduct, Long> {

    @Query("{ 'designation' :  ?0}")
    List<MongoDBProduct> findByDesignation(String designation);

    @Query("{ 'sku' :  ?0 }")
    Optional<MongoDBProduct> findBySku(String sku);

    @Query("{}")
    List<MongoDBProduct> getCatalog();

    @Query("{'sku' : ?0}")
    void deleteBySku(String sku);

//    @Query("{'_id' : ?0}")
//    Optional<MongoDBProduct> findById(String productID);
}
