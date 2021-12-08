package com.lophita.tobaskincare.persistence.repository;

import com.lophita.tobaskincare.persistence.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
