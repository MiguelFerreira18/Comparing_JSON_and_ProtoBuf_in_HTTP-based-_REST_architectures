package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.Dto.UserDto;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.repositories.UserServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public class H2UserRepoImpl implements UserServiceRepo {

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> getByUserId(Long user) {
        return repository.findById(user);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        User user = repository.findByUsername(username).orElse(null);
        if (user != null) {
            return Optional.of(user);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDto> saveUser(User user) {
        System.out.println("Saving user");
        System.out.println(user.getNif());
        repository.save(user);
        return Optional.of(new UserDto(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada()));
    }
}
