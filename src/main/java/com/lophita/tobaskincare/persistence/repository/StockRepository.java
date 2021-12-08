package com.lophita.tobaskincare.persistence.repository;

import com.lophita.tobaskincare.persistence.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
}
