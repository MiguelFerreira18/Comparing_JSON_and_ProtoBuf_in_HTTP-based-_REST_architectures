package com.conveniencestore.conveniencestore.domain.users;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "UserResponseDTO")
public record UserResponseJsonDTO(
        Integer id,
        String username,
        String email,
        UserRoles role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
