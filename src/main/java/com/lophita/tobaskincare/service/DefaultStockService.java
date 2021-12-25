package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.StockDto;
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
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public StockDto save(Stock stock) {
        Stock result = stockRepository.insert(stock);
        StockDto resultDto = StockDto.builder()
                .id(result.getId())
                .identifier(result.getIdentifier())
                .name(result.getName())
                .stockUpdated(result.getStockUpdated())
                .price(result.getPrice())
                .notes(result.getNotes())
                .urlSeller(result.getUrlSeller())
                .username(result.getUsername())
                .createdTime(result.getCreatedTime())
                .build();
        return resultDto;
    }

    @Override
    public Stock update(Stock stock) {
        return null;
    }

    @Override
    public Stock findById(Integer id) {
        return null;
    }
}
