package com.conveniencestore.conveniencestore.domain.users;

import jakarta.validation.constraints.NotNull;

public record UserAuthDTO(
        @NotNull(message = "Please provide the email.")
        String email,
        @NotNull(message = "Please provide the password.")
        String password
) {
}
