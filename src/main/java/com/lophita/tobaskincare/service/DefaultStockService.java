package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStockService implements StockService{

    @Autowired
    StockRepository stockRepository;

    @Override
    public List<Stock> findAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.insert(stock);
    }

    @Override
    public Stock update(Stock stock) {
        return null;
    }

    @Override
    public Stock findStockById(Integer id) {
        return null;
    }
}
