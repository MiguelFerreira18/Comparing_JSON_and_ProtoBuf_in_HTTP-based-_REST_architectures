package com.isep.acme.controllers.h2Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.Dto.CreateProductDTO;
import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.protobuf.CreateProductDTOOuterClass;
import com.isep.acme.protobuf.ProductDTOOuterClass;
import com.isep.acme.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void testGetCatalog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetProductBySku() throws Exception {
        // Replace "sampleSku" with an actual SKU that you want to test
        String sampleSku = "c1d4e7r8d5f2";

        ProductDTOOuterClass.ProductDTO product = ProductDTOOuterClass.ProductDTO.newBuilder().setSku("123554").setDesignation("Sample Designation").build();

        Mockito.when(productService.findBySku(sampleSku)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + sampleSku)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFindAllByDesignation() throws Exception {
        // Replace "SampleProduct" with an actual designation that you want to test

        mockMvc.perform(MockMvcRequestBuilders.get("/products/designation/SampleProduct")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateProduct() throws Exception {
        CreateProductDTO newProduct = new CreateProductDTO(" mug ", " drink something from it");

        // Serialize the newProduct object to JSON using the objectMapper
        String requestJson = objectMapper.writeValueAsString(newProduct);
        ProductDTOOuterClass.ProductDTO product = ProductDTOOuterClass.ProductDTO.newBuilder().setSku("123554").setDesignation("Sample Designation").build();
        Mockito.when(productService.create(Mockito.any(CreateProductDTOOuterClass.CreateProductDTO.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

//    @Test
//    void testUpdateProduct() throws Exception {
//        // Replace "sampleSku" with an actual SKU that you want to test
//        String sampleSku = "c1d4e7r8d5f2";
//
//        // Create a sample updated product
//        Product updatedProduct = new Product(sampleSku, "Updated Product Name", "Updated Product Description");
//
//        // Serialize the updatedProduct object to JSON using the objectMapper
//        String requestJson = objectMapper.writeValueAsString(updatedProduct);
//
//        // Mock the productService behavior for the "updateBySku" operation
//        Mockito.when(productService.updateBySku(sampleSku, updatedProduct)).thenReturn(new ProductDTO("123654", "Sample Designation"));
//
//        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + sampleSku)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    void testDeleteProduct() throws Exception {
        // Replace "sampleSku" with an actual SKU that you want to test
        String sampleSku = "c1d4e7r8d5f2";

        // Mock the productService behavior for the "deleteBySku" operation
        Mockito.doNothing().when(productService).deleteBySku(sampleSku);

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + sampleSku)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
