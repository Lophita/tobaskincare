package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    Product save(Product product);
    Product update(Product product);
    Product findProductById(Integer id);
}
