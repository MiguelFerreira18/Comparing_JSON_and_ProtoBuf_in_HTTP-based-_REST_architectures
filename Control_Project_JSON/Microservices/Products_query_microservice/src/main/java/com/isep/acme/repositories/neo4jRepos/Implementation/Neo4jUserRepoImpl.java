package com.isep.acme.repositories.neo4jRepos.Implementation;

import com.isep.acme.Dto.UserDto;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.Neo4jEntity.Neo4jUser;
import com.isep.acme.repositories.UserServiceRepo;
import com.isep.acme.repositories.neo4jRepos.Repos.Neo4JUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Neo4jUserRepoImpl implements UserServiceRepo {

    @Autowired
    private Neo4JUserRepository userRepo;


    @Transactional
    @Override
    public Optional<User> getByUserId(Long user) {
        Neo4jUser neo4jUser = userRepo.findById(user).orElse(null);
        if (neo4jUser == null) {
            return Optional.empty();
        }
    return Optional.of(new User(neo4jUser.getUserId(),neo4jUser.getUsername(), neo4jUser.getPassword(), neo4jUser.getFullName(), neo4jUser.getNif(), neo4jUser.getMorada()));
    }

    @Transactional
    @Override
    public List<User> getAllUser() {
        System.out.println("Getting all users");
        var neo4jUsers = userRepo.getAllUsers();
        System.out.println(neo4jUsers);
        for (Neo4jUser neo4jUser : neo4jUsers) {
            System.out.println(neo4jUser);
        }

        for (Neo4jUser neo4jUser : neo4jUsers) {
            System.out.println(neo4jUser.getUserId());
        }

        List<User> users = new ArrayList<>();
        for (Neo4jUser neo4jUser : neo4jUsers) {
            users.add(new User(neo4jUser.getUserId(),neo4jUser.getUsername(), neo4jUser.getPassword(), neo4jUser.getFullName(), neo4jUser.getNif(), neo4jUser.getMorada()));
        }
        return users;
    }

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        Neo4jUser user = userRepo.findByUsername(username).orElse(null);
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
        Neo4jUser neo4jUser = new Neo4jUser(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        userRepo.save(neo4jUser);
        return Optional.of(new UserDto(neo4jUser.getUsername(), neo4jUser.getPassword(), neo4jUser.getFullName(), neo4jUser.getNif(), neo4jUser.getMorada()));
    }
}
