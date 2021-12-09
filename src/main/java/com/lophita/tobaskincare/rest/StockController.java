package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.service.StockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Api(tags = "Stock")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public List<StockDto> getAllStock() {
        List<Stock> list = stockService.findAllStock();
        List<StockDto> stockDtoList = list.stream()
                .map(stock -> {
                    return StockDto.builder()
                            .id(stock.getId())
                            .identifier(stock.getIdentifier())
                            .name(stock.getName())
                            .stockUpdated(stock.getStockUpdated())
                            .price(stock.getPrice())
                            .notes(stock.getNotes())
                            .urlSeller(stock.getUrlSeller())
                            .username(stock.getUsername())
                            .createdTime(stock.getCreatedTime())
                            .build();
                }).collect(Collectors.toList());
        return stockDtoList;
    }
}
