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
        String GROUP_SKU_GENERATOR = "GroupSkuGen";
        String HASH_SKU_GENERATOR = "HashSkuGen";
        try {
//            System.out.println("Selected SKU Generation Strategy: " + skuGenerationStrategy);
            Class<?> clazz = Class.forName("com.isep.acme.generators.Sku." + skuGenerationStrategy);
//            System.out.println("Class: " + clazz.getName());
            Constructor<?> ctor = clazz.getConstructor();
//            System.out.println("Constructor: " + ctor);
            ISkuGenerator skuGenerator = (ISkuGenerator) ctor.newInstance();
//            System.out.println("ONE SKU" + skuGenerator.generateSku("ExampleOFScku"));
            return (ISkuGenerator) ctor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            return new GroupISkuGeneratorImpl();
        }
    }

    // This method is used to update the configuration property
    public ISkuGenerator skuChange(String value) {
        this.skuGenerationStrategy = value;
        return this.skuGenerator();
    }
}
