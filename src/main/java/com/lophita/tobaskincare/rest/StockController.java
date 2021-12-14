package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.service.StockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Stock")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public List<StockDto> getAllStock() {
        List<Stock> list = stockService.findAll();
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BaseResponse<StockDto> addStock(@Valid @RequestBody StockDto stockDto) {
        Stock stock = Stock.builder()
                .id(stockDto.getId())
                .identifier(stockDto.getIdentifier())
                .name(stockDto.getName())
                .stockUpdated(stockDto.getStockUpdated())
                .price(stockDto.getPrice())
                .notes(stockDto.getNotes())
                .urlSeller(stockDto.getUrlSeller())
                .username(stockDto.getUsername())
                .createdTime(stockDto.getCreatedTime())
                .build();
        StockDto result = stockService.save(stock);
        BaseResponse<StockDto> baseResponse = new BaseResponse<>("SUCCESS", "Success", result, new ArrayList<>());
        return baseResponse;
    }
}
