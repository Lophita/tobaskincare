package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllTransaction();
    Transaction save(Transaction transaction);
}
