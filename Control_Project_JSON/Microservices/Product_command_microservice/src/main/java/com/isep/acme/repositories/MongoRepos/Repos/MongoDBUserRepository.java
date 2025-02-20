package com.isep.acme.repositories.MongoRepos.Repos;

import com.isep.acme.model.MongoDbEntity.MongoDBUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile("mongodbProfile")
@Repository
public interface MongoDBUserRepository extends MongoRepository<MongoDBUser, Long> {

    @Query("{'userId': ?0}")
    Optional<MongoDBUser> findById(Long userId);

    @Query("{}")
    List<MongoDBUser> getAllUsers();

    @Cacheable
    Optional<MongoDBUser> findByUsername(String username);
}
