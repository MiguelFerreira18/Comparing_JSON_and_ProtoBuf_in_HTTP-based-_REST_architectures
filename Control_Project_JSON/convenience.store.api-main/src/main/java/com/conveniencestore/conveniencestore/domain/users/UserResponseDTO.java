package com.conveniencestore.conveniencestore.domain.users;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Integer id,
        String username,
        String email,
        UserRoles role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
