package com.isep.acme.Config;

import com.isep.acme.repositories.*;
import com.isep.acme.repositories.ApprovalServiceRepo;
import com.isep.acme.repositories.h2Repos.Implementation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(basePackages = "com.isep.acme.repositories.h2Repos", transactionManagerRef = "h2TransactionManager")
@EnableTransactionManagement
@Profile("h2Profile")
public class H2Configuration {
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "h2")
    public DataSource h2Configuration() {
        return DataSourceBuilder.create().driverClassName(driverClassName).username(username).password(password).url(url).build();
    }


    @Bean(name = "h2TransactionManager")
    public JpaTransactionManager h2TransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    //Beans for Dependency inversion principle
    @Bean
    public ProductServiceRepo h2Product() {
        return new H2ProductRepoImpl();
    }

//    @Bean
//    public RatingServiceRepo h2Rating() {
//        return new H2RatingRepositoryImpl();
//    }

    @Bean
    public UserServiceRepo h2User() {
        return new H2UserRepoImpl();
    }

//    @Bean
//    public AggregatedRatingServiceRepo h2AggregatedRating() {
//        return new H2AggregatedRatingRepoImpl();
//    }

//    @Bean
//    public ReviewServiceRepo h2Review() {
//        return new H2ReviewRepositoryImpl();
//    }
    @Bean
    public ApprovalServiceRepo h2Approval() {
        return new H2ApprovalRepoImpl();
    }

}
