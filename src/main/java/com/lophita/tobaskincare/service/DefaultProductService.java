package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.ProductDto;
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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto save(Product product) {
        Product result = productRepository.insert(product);
        ProductDto productDto = ProductDto.builder()
                .id(result.getId())
                .notes(result.getNotes())
                .type(result.getNotes())
                .identifier(result.getIdentifier())
                .name(result.getName())
                .build();
        return productDto;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }
}
