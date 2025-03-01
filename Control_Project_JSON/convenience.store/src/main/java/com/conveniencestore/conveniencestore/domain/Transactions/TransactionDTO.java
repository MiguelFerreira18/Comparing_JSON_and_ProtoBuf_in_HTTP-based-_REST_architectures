package com.conveniencestore.conveniencestore.domain.Transactions;

import jakarta.validation.constraints.NotNull;

public record TransactionDTO(
        @NotNull(message = "Please provide the client identifier.")
        String cpf,
        @NotNull(message = "Please provide the product id.")
        int productId
) {
}
