package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.users.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);

    @Query("SELECT Users FROM Users u")
    List<User> findAllFilteredResponse(Sort sort);
}
