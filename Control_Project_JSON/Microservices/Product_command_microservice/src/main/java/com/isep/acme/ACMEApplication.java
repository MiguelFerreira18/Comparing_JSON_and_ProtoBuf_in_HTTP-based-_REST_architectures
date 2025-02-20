package com.isep.acme;

import com.isep.acme.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ACMEApplication {

    public static void main(String[] args) {

//		String skuGenerationStrategy = "algorithm1"; // Default value
//
//		for (int i = 0; i < args.length; i++) {
//			if (args[i].equals("--sku-generation-strategy") && i + 1 < args.length) {
//				skuGenerationStrategy = args[i + 1];
//			}
//		}
////		skuGenerationStrategy = "GroupSkuGen";
//		System.setProperty("app.sku-generation-strategy", skuGenerationStrategy);


        SpringApplication.run(ACMEApplication.class, args);

    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }


}
