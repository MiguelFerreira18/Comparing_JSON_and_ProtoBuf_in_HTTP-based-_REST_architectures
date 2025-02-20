package com.isep.acme.generators.Sku;

import org.springframework.stereotype.Component;

@Component
public interface ISkuGenerator {
    String generateSku(String designation);
}
