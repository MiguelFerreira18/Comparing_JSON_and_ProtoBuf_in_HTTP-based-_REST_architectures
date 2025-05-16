package com.conveniencestore.conveniencestore.domain.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

@Schema(name = "EditUserDTO")
public record EditUserDTO(
        String username,
        @Email
        String email
) {
}
