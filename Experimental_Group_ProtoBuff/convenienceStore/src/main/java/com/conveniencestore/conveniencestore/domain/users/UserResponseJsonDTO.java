package com.conveniencestore.conveniencestore.domain.users;

import java.time.LocalDateTime;

public record UserResponseJsonDTO(
        Integer id,
        String username,
        String email,
        UserRoles role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
