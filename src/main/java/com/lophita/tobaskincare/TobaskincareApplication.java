package com.lophita.tobaskincare;

import com.lophita.tobaskincare.persistence.*;
import com.lophita.tobaskincare.persistence.repository.ProductRepository;
import com.lophita.tobaskincare.persistence.repository.StockRepository;
import com.lophita.tobaskincare.persistence.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class TobaskincareApplication {

    public static void main(String[] args) {
        SpringApplication.run(TobaskincareApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductRepository productRepository, TransactionRepository transactionRepository, StockRepository stockRepository) {
        return args -> {
            System.out.println("RUNNING");
            Product product = new Product("Sunscreen Wardah", "WD-001", "spf30", Type.SKINCARE.name());
            productRepository.insert(product);

            Transaction transaction = new Transaction(LocalDateTime.now(),LocalDateTime.now(), new BigDecimal("27000"),
                    "", SellingType.SHOPEE, "WD-002", "Wardah Sunscreen", "Lophita", LocalDateTime.now());
            transactionRepository.insert(transaction);

            Stock stock = new Stock("WD-003", "Bedak Compact", LocalDateTime.now(),
                    new BigDecimal("67000"), "", "https://shopee.co.id/","Lophita", LocalDateTime.now());
            stockRepository.insert(stock);
        };
    }

}
