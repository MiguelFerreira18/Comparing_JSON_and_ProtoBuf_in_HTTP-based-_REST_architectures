package com.conveniencestore.conveniencestore.domain.ProductEntity;

import jakarta.validation.constraints.NotNull;

public record ProductEntityDTO(
        @NotNull(message = "Please provide the name.")
        String name
) {
}
