package com.conveniencestore.conveniencestore.domain.users;

import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull(message = "Please provide the username.")
        String username,
        @NotNull(message = "Please provide the email.")
        String email,
        String password,
        @NotNull(message = "Please provide the user role.")
        UserRoles role
) {
}
