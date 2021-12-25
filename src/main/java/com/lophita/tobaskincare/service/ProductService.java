package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto save(Product product);
    Product update(Product product);
    ProductDto findById(String id);
}
