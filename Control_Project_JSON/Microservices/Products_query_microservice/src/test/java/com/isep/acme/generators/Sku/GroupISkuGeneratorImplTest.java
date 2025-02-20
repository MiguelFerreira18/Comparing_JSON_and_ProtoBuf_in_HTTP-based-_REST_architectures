package com.isep.acme.generators.Sku;

import com.isep.acme.model.H2Entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.*;

@Profile("h2Profile")
class GroupISkuGeneratorImplTest {

    @Test
    void generateSkuShouldReturnValidSku() {
        GroupISkuGeneratorImpl skuGenerator = new GroupISkuGeneratorImpl();
        Product product = new Product("", "Sample Product", "Description");

        String sku = skuGenerator.generateSku(product.getDesignation());

        // Assert that the generated SKU is not null
        assertNotNull(sku);

        // Assert that the generated SKU has the correct length (should be 8 characters)
        assertTrue(sku.length() >= 8, "Error, random is too high");
        assertTrue(sku.length()  <= 12, "Error, random is too low");

        // Assert that the generated SKU matches the expected pattern (3 digits, 3 lowercase letters, 1 digit, 1 special character)
        //assertTrue(sku.matches("\\d{3}[a-z]{3}\\d[a-z][!@#\\$%^&*()_\\-+\\=\\[\\]{};:,.<>?]"));
    }

    @Test
    void generateSkuShouldReturnUniqueSku() {
        GroupISkuGeneratorImpl skuGenerator = new GroupISkuGeneratorImpl();
        Product product1 = new Product("", "Product 1", "Description");
        Product product2 = new Product("", "Product 2", "Description");

        String sku1 = skuGenerator.generateSku(product1.getDesignation());
        String sku2 = skuGenerator.generateSku(product2.getDesignation());

        // Assert that the SKUs generated for different products are not the same
        assertNotEquals(sku1, sku2);
    }
}
