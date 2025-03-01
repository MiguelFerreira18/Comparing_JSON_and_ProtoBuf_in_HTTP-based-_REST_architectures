package com.conveniencestore.conveniencestore.domain.Products;

import jakarta.validation.constraints.NotNull;

public record ProductStatusDTO(
        @NotNull(message = "Please provide this item 'sold' status.")
        boolean sold
) {
}
