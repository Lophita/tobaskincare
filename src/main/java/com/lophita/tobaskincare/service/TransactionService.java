package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction save(Transaction transaction);
}
