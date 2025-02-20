package com.isep.acme.generators.Sku;

import com.isep.acme.model.H2Entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;


import static org.junit.jupiter.api.Assertions.*;

@Profile("h2Profile")
@DisplayName("HashSkuGeneratorImplTest")
class HashISkuGeneratorImplTest {

    static ISkuGenerator generator;

    @BeforeAll
    public static void setUp() throws Exception {
        generator = new HashISkuGeneratorImpl();
    }

    @Test
    @DisplayName("Test generateSku with a normal input")

    public void testGenerateSkuValidInput() {
        Product product = new Product("testSku", "TestProduct", "Description");

        String sku = generator.generateSku(product.getDesignation());
        product.setSku(sku);

        assertNotNull(sku);
        assertTrue(sku.length() >= 8 && sku.length() <= 12);
        assertTrue(sku.matches("[0-9a-f]+"));
        assertEquals(product.getSku(), sku);
    }

    @Test
    @DisplayName("Test generateSku with a null product")
    public void testGenerateSkuNullProduct() {
        try {
            generator.generateSku(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    @DisplayName("Test generateSku with a empty designation")
    public void testGenerateSkuEmptyDesignation() {
        try {
        Product product = new Product("testSku", "", "Description");
            generator.generateSku(product.getDesignation());
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    @DisplayName("Test generateSku with a OddLength designation")
    public void testGenerateSkuOddLength() {
        Product product = new Product("testSku", "OddLength", "Description");

        String sku = generator.generateSku(product.getDesignation());
        product.setSku(sku);
        assertNotNull(sku);
        assertTrue(sku.length() >= 8 && sku.length() <= 12);
        assertTrue(sku.matches("[0-9a-f]+"));
        assertEquals(product.getSku(), sku);
    }

    @Test
    @DisplayName("Test generateSku with a short designation")
    public void testGenerateSkuShortInput() {

        Product product = new Product("testSku", "Short", "Description");


        String sku = generator.generateSku(product.getDesignation());
        product.setSku(sku);


        assertNotNull(sku);
        assertTrue(sku.length() >= 8 && sku.length() <= 12);
        assertTrue(sku.matches("[0-9a-f]+"));
        assertEquals(product.getSku(), sku);
    }

    @Test
    @DisplayName("Test generateSku with a long designation")
    public void testGenerateSkuLongInput() {
        Product product = new Product("testSku", "VeryLongDesignationThatExceedsTheLimit", "Description");

        String sku = generator.generateSku(product.getDesignation());
        product.setSku(sku);

        assertNotNull(sku);
        assertTrue(sku.length() >= 8 && sku.length() <= 12);
        assertTrue(sku.matches("[0-9a-f]+"));
        assertEquals(product.getSku(), sku);
    }



}