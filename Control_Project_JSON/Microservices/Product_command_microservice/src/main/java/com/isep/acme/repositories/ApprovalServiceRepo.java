package com.isep.acme.repositories;

import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;


public interface ApprovalServiceRepo {
    public void approveProduct(ProductAcceptanceEvent productAcceptanceEvent);
    public int countProductApprovals(Long productID);
}
