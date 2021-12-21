package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    ProductDto save(Product product);
    Product update(Product product);
    Product findById(String id);
}
