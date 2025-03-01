package com.conveniencestore.conveniencestore.infra.security;

import java.time.Instant;

public record TokenResponseDTO(
        String token,
        Instant expiresAt
) {
}
