package com.isep.acme.repositories.MongoRepos.Implementation;

import com.isep.acme.Dto.UserDto;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.MongoDbEntity.MongoDBUser;
import com.isep.acme.repositories.MongoRepos.Repos.MongoDBUserRepository;
import com.isep.acme.repositories.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MongoUserRepoImpl implements UserServiceRepo {

    @Autowired
    private MongoDBUserRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<User> getByUserId(Long user) {
        Optional<MongoDBUser> userId = repository.findById(user);
        if(userId.isEmpty()){
            return Optional.empty();
        } else {
            User user1 = new User(userId.get().getUserId() ,userId.get().getUsername(), userId.get().getPassword(), userId.get().getFullName(), userId.get().getNif(), userId.get().getMorada());
            user1.setAuthorities(userId.get().getAuthorities());
            return Optional.of(user1);
        }
    }

    @Override
    public List<User> getAllUser() {
        try {
            Query query = new Query();
            List<MongoDBUser> usersMongo = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                Optional<MongoDBUser> userId = repository.findById((long) i);
                if (userId.isEmpty()) {
                    continue;
                } else {
                    usersMongo.add(userId.get());
                }
            }

            List<User> users = new ArrayList<>();
            for (MongoDBUser user : usersMongo) {
                users.add(new User(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada()));
            }

            return users;
        } catch (Exception e) {
            // Handle exceptions, log errors, or rethrow as needed
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list or throw an exception
        }
    }


    @Override
    public Optional<UserDetails> findByUsername(String username) {
        MongoDBUser user = repository.findByUsername(username).orElse(null);
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
        MongoDBUser mongoDBUser = new MongoDBUser(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        repository.save(mongoDBUser);
        return Optional.of(new UserDto(mongoDBUser.getUsername(), mongoDBUser.getPassword(), mongoDBUser.getFullName(), mongoDBUser.getNif(), mongoDBUser.getMorada()));
    }
}
