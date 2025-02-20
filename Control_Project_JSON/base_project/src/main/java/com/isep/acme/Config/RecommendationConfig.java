package com.isep.acme.Config;

import com.isep.acme.generators.Recomendation.ReviewRecomendationGenerator;
import com.isep.acme.generators.Recomendation.UserReviewGeneratorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Configuration
public class RecommendationConfig {
    @Value("${app.recommendation-algorithm}")
    private String RecomendationGenerationStrategy;

    @Bean
    public ReviewRecomendationGenerator recomendationGeneratorConfig() {
        try {
            Class<?> clazz = Class.forName("com.isep.acme.generators.Recomendation." + RecomendationGenerationStrategy);
            Constructor<?> ctor = clazz.getConstructor();
            return (ReviewRecomendationGenerator) ctor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            return new UserReviewGeneratorImpl();
        }
    }
}
