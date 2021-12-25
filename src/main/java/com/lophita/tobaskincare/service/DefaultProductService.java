package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.persistence.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultProductService implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = products.stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .identifier(product.getIdentifier())
                        .type(product.getType())
                        .notes(product.getNotes())
                        .build())
                .collect(Collectors.toList());
        return productDtoList;
    }

    @Override
    public ProductDto save(Product product) {
        Product result = productRepository.insert(product);
        ProductDto productDto = ProductDto.builder()
                .id(result.getId())
                .notes(result.getNotes())
                .type(result.getType())
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
    public ProductDto findById(String id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()){
            ProductDto productDto = ProductDto.builder()
                    .id(result.get().getId())
                    .notes(result.get().getNotes())
                    .type(result.get().getType())
                    .identifier(result.get().getIdentifier())
                    .name(result.get().getName())
                    .build();
            return productDto;
        }
        else {
            log.error("/product/{}: Product with id Not Found", id);
            throw new NoSuchElementException("Product with Id is not Found");
        }
    }
}
