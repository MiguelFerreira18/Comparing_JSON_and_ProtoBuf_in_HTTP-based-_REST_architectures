package com.conveniencestore.conveniencestore.domain.users;

import java.time.Instant;

public record LoginResponseDTO(
        String token,
        Instant expiresAt,
        UserResponseJsonDTO user
) {
}
