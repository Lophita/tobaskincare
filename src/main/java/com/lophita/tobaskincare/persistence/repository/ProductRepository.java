package com.lophita.tobaskincare.persistence.repository;

import com.lophita.tobaskincare.persistence.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
