package com.isep.acme.services;

import com.isep.acme.Dto.ReviewDTO;

import java.util.List;

public interface ReviewRecomendationService {
    public List<ReviewDTO> getRecomendations(Long userId);
}
