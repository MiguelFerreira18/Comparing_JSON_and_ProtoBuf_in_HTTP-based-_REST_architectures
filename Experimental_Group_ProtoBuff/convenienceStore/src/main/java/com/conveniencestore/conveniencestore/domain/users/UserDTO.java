package com.conveniencestore.conveniencestore.domain.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UserDTO")
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
