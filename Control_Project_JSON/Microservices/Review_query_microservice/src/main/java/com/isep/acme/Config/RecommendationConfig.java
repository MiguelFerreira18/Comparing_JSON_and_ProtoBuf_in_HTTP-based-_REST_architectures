package com.isep.acme.Config;

import com.isep.acme.generators.Sku.Recomendation.ReviewRecomendationGenerator;
import com.isep.acme.generators.Sku.Recomendation.UserReviewGeneratorImpl;
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
        System.out.println("Selected Recomendation Strategy: " + RecomendationGenerationStrategy);
        try {
            System.out.println("Selected Recomendation Strategy: " + RecomendationGenerationStrategy);
            System.out.println("com.isep.acme.generators.Recomendation." + RecomendationGenerationStrategy);
            Class<?> clazz = Class.forName("com.isep.acme.generators.Recomendation." + RecomendationGenerationStrategy);
            System.out.println("Class: " + clazz.getName());
            Constructor<?> ctor = clazz.getConstructor();
            System.out.println("Constructor: " + ctor);
            return (ReviewRecomendationGenerator) ctor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            return new UserReviewGeneratorImpl();
        }
    }
}
