package com.isep.acme.repositories.neo4jRepos.Repos;

import com.isep.acme.model.Neo4jEntity.Neo4jUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile("neo4jProfile")
@Repository
@CacheConfig(cacheNames = "users")
public interface Neo4JUserRepository extends Neo4jRepository<Neo4jUser, Long> {

    @Query("MATCH (u:Neo4jUser) WHERE id(u) = $userId RETURN u")
    Optional<Neo4jUser> findById(Long userId);

    @Query("MATCH (u:Neo4jUser) WHERE u.username = $username RETURN u")
    Optional<Neo4jUser> findByUsername(String username);

    @Query("MATCH (r:Neo4jReview)-[:WRITTEN_BY]->(u:Neo4jUser) WHERE toLower(r.approvalStatus) = toLower($status) AND id(r) = $reviewId RETURN u LIMIT 1")
    Optional<Neo4jUser> getUserFromReview(@Param("reviewId" ) Long reviewId, @Param("status") String status);

    @Query("MATCH (u:Neo4jUser) where id(u) <> 0 RETURN collect(u)")
    List<Neo4jUser> getAllUsers();
}