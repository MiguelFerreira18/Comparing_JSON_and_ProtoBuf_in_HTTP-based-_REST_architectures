package com.isep.acme.Mapper;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.MongoDbEntity.MongoDBProduct;
import com.isep.acme.model.Neo4jEntity.Neo4jProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(target = "sku", source = "sku")
    @Mapping(target = "designation", source = "designation")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "published", source = "published")
    Product toProductFromNeo4j(Neo4jProduct neo4jProduct);

    @Mapping(target = "sku", source = "sku")
    @Mapping(target = "designation", source = "designation")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "published", source = "published")
    Product toProductFromMongoDB(MongoDBProduct mongoDBProduct);

    @Mapping(target = "sku", source = "sku")
    @Mapping(target = "designation", source = "designation")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "published", source = "published")
    Neo4jProduct toNeo4jFromProduct(Product product);

    @Mapping(target = "sku", source = "sku")
    @Mapping(target = "designation", source = "designation")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "published", source = "published")
    MongoDBProduct toMongoDBFromProduct(Product product);


}
