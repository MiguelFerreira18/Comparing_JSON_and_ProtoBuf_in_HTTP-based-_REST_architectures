package com.conveniencestore.conveniencestore.domain.Products;

import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotNull(message = "Please provide the entity id.")
        int entityId
) {
}
