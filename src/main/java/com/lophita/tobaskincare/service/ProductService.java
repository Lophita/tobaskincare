package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    Product update(Product product);
    Product findById(String id);
}
