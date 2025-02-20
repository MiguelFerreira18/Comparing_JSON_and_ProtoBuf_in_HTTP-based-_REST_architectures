package com.isep.acme.services;

import com.isep.acme.Mapper.UserViewMapper;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.View.UserView;
import com.isep.acme.repositories.UserServiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private final UserServiceRepo userRepo;
    @Autowired
    private final UserViewMapper userViewMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username - %s, not found", username)));
    }

    @Override
    public UserView getUser(final Long userId) {
        Optional<User> user = userRepo.getByUserId(userId);
        if (user.isEmpty()) {
            return null;
        } else {
            return userViewMapper.toUserView(user.get());
        }
    }

    @Override
    public Optional<User> getUserId(Long user) {
        return userRepo.getByUserId(user);
    }
}
