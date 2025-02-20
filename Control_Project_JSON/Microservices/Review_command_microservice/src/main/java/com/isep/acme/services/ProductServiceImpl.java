package com.isep.acme.services;

import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.Dto.ProductDetailDTO;
import com.isep.acme.generators.Sku.ISkuGenerator;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.Role;
import com.isep.acme.repositories.ProductServiceRepo;
import com.isep.acme.repositories.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductServiceRepo repository;
    @Autowired
    private UserServiceRepo userServiceRepo;

    @Autowired
    private ISkuGenerator skuGenerator;
    private Integer senderID = (int) (Math.random() * 1000) + (int) (Math.random() * 1000);

    @Override
    public Optional<Product> getProductBySku(final String sku) {

        return repository.findBySku(sku).get().isPublished() ? repository.findBySku(sku) : null;
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);
        if (product.isEmpty() && product.get().isPublished() ) {
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
        Iterable<Product> p = repository.getCatalog();
        System.out.println("Getting catalog" + p.toString());
        List<ProductDTO> pDto = new ArrayList();
        for (Product pd : p) {
            if(pd.isPublished()) {
                pDto.add(pd.toDto());
            }
        }
        System.out.println("Getting catalog" + pDto.size());
        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<Product> p = repository.findBySku(sku);

        return p.filter(Product::isPublished).map(product -> new ProductDetailDTO(product.getSku(), product.getDesignation(), product.getDescription())).orElse(null);
    }


    @Override
    public ProductDTO create(final Product product) {
        System.out.println(skuGenerator.generateSku(product.getDesignation()));
        if (product.getSku() != null) {
            final Product p = new Product(product.getSku(), product.getDesignation(), product.getDescription());
            return repository.save(p).toDto();
        }
        Product p = new Product(skuGenerator.generateSku(product.getDesignation()), product.getDesignation(), product.getDescription());

        return repository.save(p).toDto();
    }

    @Override
    public Integer getSenderID() {
        return senderID;
    }

    @Override
    public Integer updateBySku(String sku, Product product) {
        return 0;
    }

    @Override
    public void deleteBySku(String sku) {
//        producer.deletePoductMessage(senderID, repository.findBySku(sku).get());
        repository.deleteBySku(sku);
    }

    @Override
    public void addApproval(String sku) {
    }

    @Override
    public Boolean publish(String sku) {
        return true;
    }


    private boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return false;
        return authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().toLowerCase().contains(role.toLowerCase())) || authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().toLowerCase().contains(Role.Admin.toLowerCase()));
    }

}
