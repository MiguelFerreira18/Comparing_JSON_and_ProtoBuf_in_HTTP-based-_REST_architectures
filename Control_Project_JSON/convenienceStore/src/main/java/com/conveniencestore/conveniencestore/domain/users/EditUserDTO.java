package com.conveniencestore.conveniencestore.domain.users;

import jakarta.validation.constraints.Email;

public record EditUserDTO(
        String username,
        @Email
        String email
) {
}
