package com.isep.acme.repositories.neo4jRepos.Implementation;


import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;
import com.isep.acme.model.Neo4jEntity.Neo4jProduct;
import com.isep.acme.model.Neo4jEntity.Neo4jProductAcceptanceEvent;
import com.isep.acme.model.Neo4jEntity.Neo4jUser;
import com.isep.acme.repositories.ApprovalServiceRepo;
import com.isep.acme.repositories.neo4jRepos.Repos.Neo4jProductEventsRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

public class Neo4jApprovalRepoImp implements ApprovalServiceRepo {
    @Autowired
    private Neo4jProductEventsRepository repository;

    @Override
    public void approveProduct(ProductAcceptanceEvent productAcceptanceEvent) {
        Neo4jProduct neo4jProduct = new Neo4jProduct(
                productAcceptanceEvent.getProduct().getProductID(),
                productAcceptanceEvent.getProduct().getSku(),
                productAcceptanceEvent.getProduct().getDesignation(),
                productAcceptanceEvent.getProduct().getDescription(),
                productAcceptanceEvent.getProduct().isPublished()
        );

        // Create Neo4jUser from ProductAcceptanceEvent
        Neo4jUser neo4jUser = new Neo4jUser(
                productAcceptanceEvent.getUser().getUsername(),
                productAcceptanceEvent.getUser().getPassword(),
                productAcceptanceEvent.getUser().getFullName(),
                productAcceptanceEvent.getUser().getNif(),
                productAcceptanceEvent.getUser().getMorada()
        );
        repository.save(new Neo4jProductAcceptanceEvent(neo4jProduct, neo4jUser));
    }

    @Override
    public int countProductApprovals(Long productID) {
        return repository.countProductApprovals(productID);
    }
}
