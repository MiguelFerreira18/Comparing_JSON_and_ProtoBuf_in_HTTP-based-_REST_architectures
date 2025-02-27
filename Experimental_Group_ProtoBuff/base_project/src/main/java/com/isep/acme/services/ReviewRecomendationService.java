package com.isep.acme.services;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.protobuf.ReviewDTOOuterClass;

import java.util.List;

public interface ReviewRecomendationService {
    public ReviewDTOOuterClass.ReviewCatalog getRecomendations(Long userId);
}
