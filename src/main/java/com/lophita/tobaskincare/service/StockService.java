package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.persistence.Stock;

import java.util.List;

public interface StockService {
    List<Stock> findAllStock();
    Stock save(Stock stock);
    Stock update(Stock stock);
    Stock findStockById(Integer id);
}
