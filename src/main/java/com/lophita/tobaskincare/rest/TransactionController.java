package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.persistence.Transaction;
import com.lophita.tobaskincare.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public List<Transaction> getAllTransaction() {
        return null;
    }
}
