package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;

import java.util.List;

public interface StockService {
    List<Stock> findAll();
    StockDto save(Stock stock);
    Stock update(Stock stock);
    Stock findById(Integer id);
}
