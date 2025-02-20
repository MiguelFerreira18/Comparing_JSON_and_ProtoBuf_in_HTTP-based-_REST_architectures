package com.isep.acme.Config;

import com.isep.acme.repositories.*;
import com.isep.acme.repositories.neo4jRepos.Implementation.*;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableNeo4jRepositories(basePackages = "com.isep.acme.repositories.neo4jRepos", transactionManagerRef = "neo4jTransactionManager")
@EnableTransactionManagement
@Profile("neo4jProfile")
public class Neo4jConfig extends Neo4jDataAutoConfiguration {
    @Value("${spring.neo4j.uri}")
    private String uri;
    @Value("${spring.neo4j.authentication.username}")
    private String username;
    @Value("${spring.neo4j.authentication.password}")
    private String password;

    @Bean
    public Driver getConfiguration() {
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    @Bean(name = "neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager() {
        return new Neo4jTransactionManager(getConfiguration());
    }

    @Bean
    public Neo4jClient neo4jClient() {
        return Neo4jClient.create(getConfiguration());
    }


    //Beans for Dependency inversion principle
    @Bean
    public ProductServiceRepo neo4jProduct() {
        return new Neo4jProductRepoImpl();
    }

    @Bean
    public UserServiceRepo neo4jUser() {
        return new Neo4jUserRepoImpl();
    }


    @Bean
    public ApprovalServiceRepo neo4jProductAcceptanceEvent() {
        return new Neo4jApprovalRepoImp();
    }


}
