package com.isep.acme.Config;

import com.isep.acme.repositories.*;
import com.isep.acme.repositories.MongoRepos.Implementation.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoRepositories(basePackages = "com.isep.acme.repositories.MongoRepos")
@EnableTransactionManagement
@Profile("mongodbProfile")
public class MongoConfig extends AbstractMongoClientConfiguration { //extends AbstractMongoClientConfiguration
@Value("${spring.data.mongodb.database}")
private String databaseName;
@Value("${spring.data.mongodb.uri}")
private String databaseUri;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(databaseUri);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    //create a full config for Mongodb
    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        System.out.println("MongoTransactionManager");
        return new MongoTransactionManager(dbFactory);
    }


    //Beans for Dependency inversion principle
    @Bean
    public ProductServiceRepo mongodbProduct() {
        return new MongoProductRepoImpl();
    }

    @Bean
    public UserServiceRepo mongodbUser() {
        return new MongoUserRepoImpl();
    }

    @Bean
    public ApprovalServiceRepo mongodbProductAcceptanceEvent() {
        return new MongoApprovalRepoImpl();
    }


}
