package com.isep.acme.services;

import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.View.UserView;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    public UserView getUser(Long userId);

    public Optional<User> getUserId(Long user);

    public UserDetails loadUserByUsername(final String username);

}
