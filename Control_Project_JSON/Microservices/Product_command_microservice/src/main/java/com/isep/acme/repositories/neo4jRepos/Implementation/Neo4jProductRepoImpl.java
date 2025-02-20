package com.isep.acme.repositories.neo4jRepos.Implementation;

import com.isep.acme.Mapper.ProductMapper;
import com.isep.acme.generators.Sku.ISkuGenerator;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.Neo4jEntity.Neo4jProduct;
import com.isep.acme.repositories.ProductServiceRepo;
import com.isep.acme.repositories.neo4jRepos.Repos.Neo4jProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Neo4jProductRepoImpl implements ProductServiceRepo {

    @Autowired
    Neo4jProductRepository repository;
    @Autowired
    private ISkuGenerator skuGenerator;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<Product> findByDesignation(String designation) {
        List<Neo4jProduct> p = repository.findByDesignation(designation);
        List<Product> products = new ArrayList<>();

        for (Neo4jProduct pd : p) {
            products.add(productMapper.toProductFromNeo4j(pd));
        }

        return products;
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        Optional<Neo4jProduct> product = repository.findBySku(sku);
        return product.map(productMapper::toProductFromNeo4j);
    }

    @Override
    public Iterable<Product> getCatalog() {
        List<Neo4jProduct> product = repository.getCatalog();
        List<Product> products = new ArrayList<>();

        for (Neo4jProduct pd : product) {
            products.add(productMapper.toProductFromNeo4j(pd));
        }
        return products;
    }

    @Override
    public Product save(Product product) {
        System.out.println("Saving product");
        String sku = product.getSku();
        if(sku == null || sku.length() < 8 || sku.length() > 12){
            sku = skuGenerator.generateSku(product.getDesignation());
        }
        Neo4jProduct neo4jProduct = new Neo4jProduct(sku, product.getDesignation(), product.getDescription());
        repository.save(neo4jProduct);
        return productMapper.toProductFromNeo4j(neo4jProduct);
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    @Override
    public Integer updateBySku(String sku, Product product) {
        try{
        Neo4jProduct neo4jProduct = repository.findBySku(sku).get();
        neo4jProduct.setDesignation(product.getDesignation());
        neo4jProduct.setDescription(product.getDescription());
        neo4jProduct.setPublished(product.isPublished());
        repository.save(neo4jProduct);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public List<Product> findAll() {
        List<Neo4jProduct> product = repository.findAll();
        List<Product> products = new ArrayList<>();

        for (Neo4jProduct pd : product) {
            products.add(productMapper.toProductFromNeo4j(pd));
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long productID) {
        Optional<Neo4jProduct> product = repository.findById(productID);
        return product.map(productMapper::toProductFromNeo4j);
//        return product.map(neo4jProduct -> new Product(neo4jProduct.getSku(), neo4jProduct.getDesignation(), neo4jProduct.getDescription()));
    }
}
