package com.isep.acme.services;

import com.isep.acme.Dto.CreateProductDTO;
import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.Dto.ProductDetailDTO;
import com.isep.acme.generators.Sku.ISkuGenerator;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.repositories.ProductServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductServiceRepo repository;

    @Autowired
    private ISkuGenerator skuGenerator;

    @Override
    public Optional<Product> getProductBySku(final String sku) {

        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        if (product.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(product.get().toDto());
        }
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList();
        for (Product pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<Product> p = repository.findBySku(sku);

        if (p.isEmpty())
            return null;
        else
            return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());
    }


    @Override
    public ProductDTO create(final CreateProductDTO product) {
        final Product p = new Product(skuGenerator.generateSku(product.getDesignation()), product.getDesignation(), product.getDescription());
        return repository.save(p).toDto();
    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {
        return repository.updateBySku(assignNewProduct(sku, product)).toDto();
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    private Product assignNewProduct(String sku, Product newProduct) {
        Product product = repository.findBySku(sku).get();
        product.updateProduct(newProduct);
        return product;
    }
}
