package com.isep.acme.repositories.MongoRepos.Implementation;

import com.isep.acme.Mapper.ProductMapper;
import com.isep.acme.generators.Sku.ISkuGenerator;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.MongoDbEntity.MongoDBProduct;
import com.isep.acme.repositories.MongoRepos.Repos.MongoDBProductRepository;
import com.isep.acme.repositories.ProductServiceRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoProductRepoImpl implements ProductServiceRepo {

    @Autowired
    private MongoDBProductRepository repository;
    @Autowired
    private ISkuGenerator skuGenerator;
    @Autowired
    private MongoTemplate mongoTemplate;

    //Mappers
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<Product> findByDesignation(String designation) {
        List<MongoDBProduct> p = repository.findByDesignation(designation);
        List<Product> products = new ArrayList<>();

        for (MongoDBProduct pd : p) {
            products.add(new Product(pd.getSku(), pd.getDesignation(), pd.getDescription()));
        }
        return products;
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        Optional<MongoDBProduct> product = repository.findBySku(sku);
        return product.map(mongoDBProduct -> new Product(mongoDBProduct.getSku(), mongoDBProduct.getDesignation(), mongoDBProduct.getDescription()));
    }

    @Override
    public Iterable<Product> getCatalog() {
        List<MongoDBProduct> product = repository.getCatalog();
        List<Product> products = new ArrayList<>();

        for (MongoDBProduct pd : product) {
            System.out.println(pd.getSku() + " " + pd.getDesignation() + " " + pd.getDescription());
        }

        for (MongoDBProduct pd : product) {
            products.add(new Product(pd.getSku(), pd.getDesignation(), pd.getDescription()));
        }
        return products;
    }

    @Override
    public Product save(Product product) {
        System.out.println("SKU:" + product.getSku());

        if (product.getSku() == null || product.getSku().isEmpty() || product.getSku().length() < 8 || product.getSku().length() > 12) {
            String sku = skuGenerator.generateSku(product.getDesignation());
            MongoDBProduct mongoDBProduct = new MongoDBProduct(sku, product.getDesignation(), product.getDescription());
            repository.save(mongoDBProduct);
        } else {
            MongoDBProduct mongoDBProduct = new MongoDBProduct(product.getSku(), product.getDesignation(), product.getDescription());
            repository.save(mongoDBProduct);
        }

        return product;
    }

    @Override
    public void deleteBySku(String sku) {
        Query query = new Query(Criteria.where("sku").is(sku));
        mongoTemplate.remove(query, MongoDBProduct.class);
    }

    @Override
    public Integer updateBySku(String sku, Product product) {
            try {
            MongoDBProduct productInDataBase = repository.findBySku(sku).get();
            productInDataBase.setSku(sku);
            productInDataBase.setDesignation(product.getDesignation());
            productInDataBase.setDescription(product.getDescription());
            productInDataBase.setPublished(product.isPublished());
            //repository.delete(product.get());
            MongoDBProduct p = repository.save(productInDataBase);
            } catch (Exception e) {
                return 0;
            }
            return 1;
    }

    @Override
    public List<Product> findAll() {
        List<MongoDBProduct> p = repository.findAll();
        List<Product> products = new ArrayList<>();

        for (MongoDBProduct pd : p) {
            products.add(new Product(pd.getSku(), pd.getDesignation(), pd.getDescription()));
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long productID) {
        Optional<MongoDBProduct> product = repository.findById(productID);
        return product.map(mongoDBProduct -> new Product(mongoDBProduct.getSku(), mongoDBProduct.getDesignation(), mongoDBProduct.getDescription()));
    }
}
