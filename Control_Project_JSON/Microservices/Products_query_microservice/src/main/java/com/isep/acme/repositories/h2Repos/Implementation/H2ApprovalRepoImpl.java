package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;
import com.isep.acme.repositories.ApprovalServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.ProductsEvents;
import org.springframework.beans.factory.annotation.Autowired;

public class H2ApprovalRepoImpl implements ApprovalServiceRepo {
    @Autowired
    private ProductsEvents repository;
    @Override
    public void approveProduct(ProductAcceptanceEvent productAcceptanceEvent) {
        repository.save(productAcceptanceEvent);
    }

    @Override
    public int countProductApprovals(Long productID) {
        return repository.countProductApprovals(productID);
    }
}
