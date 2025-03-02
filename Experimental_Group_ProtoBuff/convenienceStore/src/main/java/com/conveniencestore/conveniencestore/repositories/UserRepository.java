package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserResponseJsonDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);

    @Query("SELECT new com.conveniencestore.conveniencestore.domain.users.UserResponseJsonDTO(u.id, u.username, u.email, u.role, u.createdAt, u.updatedAt) FROM Users u")
    List<UserResponseJsonDTO> findAllFilteredResponse(Sort sort);
}
