package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }
}
