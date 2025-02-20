package com.isep.acme.repositories.MongoRepos.Implementation;


import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;
import com.isep.acme.model.MongoDbEntity.MongoDBProduct;
import com.isep.acme.model.MongoDbEntity.MongoDBUser;
import com.isep.acme.model.MongoDbEntity.MongoProductAcceptanceEvent;
import com.isep.acme.model.Neo4jEntity.Neo4jProduct;
import com.isep.acme.model.Neo4jEntity.Neo4jUser;
import com.isep.acme.repositories.ApprovalServiceRepo;
import com.isep.acme.repositories.MongoRepos.Repos.MongoDbProductEventsRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoApprovalRepoImpl implements ApprovalServiceRepo {
    @Autowired
    private MongoDbProductEventsRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void approveProduct(ProductAcceptanceEvent productAcceptanceEvent) {

        MongoDBProduct mongoDBProduct = new MongoDBProduct(
                productAcceptanceEvent.getProduct().getProductID(),
                productAcceptanceEvent.getProduct().getSku(),
                productAcceptanceEvent.getProduct().getDesignation(),
                productAcceptanceEvent.getProduct().getDescription(),
                productAcceptanceEvent.getProduct().isPublished()
        );

        // Create Neo4jUser from ProductAcceptanceEvent
        MongoDBUser user = new MongoDBUser(
                productAcceptanceEvent.getUser().getUsername(),
                productAcceptanceEvent.getUser().getPassword(),
                productAcceptanceEvent.getUser().getFullName(),
                productAcceptanceEvent.getUser().getNif(),
                productAcceptanceEvent.getUser().getMorada()
        );

        repository.save(new MongoProductAcceptanceEvent(mongoDBProduct, user));
    }

    @Override
    public int countProductApprovals(Long productID) {
        return Long.valueOf(mongoTemplate.count(Query.query(Criteria.where("productID").is(productID)), MongoDBProduct.class)).intValue();
    }
}
