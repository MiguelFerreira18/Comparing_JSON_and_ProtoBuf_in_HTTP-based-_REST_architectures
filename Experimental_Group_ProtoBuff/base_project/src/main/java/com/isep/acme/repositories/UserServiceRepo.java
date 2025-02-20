package com.isep.acme.repositories;

import com.isep.acme.Dto.UserDto;
import com.isep.acme.model.H2Entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserServiceRepo {


    public Optional<User> getByUserId(Long user);

    public List<User> getAllUser();

    public Optional<UserDetails> findByUsername(final String username);
    public Optional<UserDto> saveUser(User user);
}
