package com.isep.acme.Config;


import com.isep.acme.generators.Sku.GroupISkuGeneratorImpl;
import com.isep.acme.generators.Sku.ISkuGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Configuration
public class SkuConfig {
    @Value("${app.sku-generation-strategy}")
    private String skuGenerationStrategy;

    @Bean
    public ISkuGenerator skuGenerator() {
        try {
            Class<?> clazz = Class.forName("com.isep.acme.generators.Sku." + skuGenerationStrategy);
            Constructor<?> ctor = clazz.getConstructor();
            return (ISkuGenerator) ctor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            return new GroupISkuGeneratorImpl();
        }
    }

    public ISkuGenerator skuChange(String value) {
        this.skuGenerationStrategy = value;
        return this.skuGenerator();
    }
}
