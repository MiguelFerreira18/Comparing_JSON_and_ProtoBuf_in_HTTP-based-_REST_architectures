package com.conveniencestore.conveniencestore.domain.users;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    EMPLOYEE("employee");
    private final String role;
    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
