package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.persistence.repository.StockRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @InjectMocks
    DefaultStockService stockService;

    @Mock
    StockRepository stockRepository;

    List<Stock> stocks;

    @Before
    public void setUp(){
        stocks = new ArrayList<>();
        Stock stock = Stock.builder()
                .id("this-id-add-stock-test")
                .identifier("SKU-SCRT-001-009")
                .name("Scarlett Whitening Lotion")
                .notes("This Product Will expired on July 19, 2024")
                .price(BigDecimal.valueOf(55000))
                .urlSeller("shopee.co.id")
                .username("Lophita")
                .stockUpdated(LocalDateTime.now())
                .createdTime(LocalDateTime.now())
                .build();
        Stock stock2 = Stock.builder()
                .id("this-id-add-stock2-test")
                .identifier("SKU-SCRT-001-009")
                .name("Scarlett Whitening Lotion")
                .notes("This Product Will expired on July 19, 2024")
                .price(BigDecimal.valueOf(55000))
                .urlSeller("shopee.co.id")
                .username("Lophita")
                .stockUpdated(LocalDateTime.now())
                .createdTime(LocalDateTime.now())
                .build();
        stocks.add(stock);
        stocks.add(stock2);
    }

    @After
    public void tearDown(){
        stocks = null;
    }

    @Test
    public void givenStockToAddShouldReturnAddedStock(){
        Stock stock = Stock.builder()
                .id("this-id-add-stock-test")
                .identifier("SKU-SCRT-001-009")
                .name("Scarlett Whitening Lotion")
                .notes("This Product Will expired on July 19, 2024")
                .price(BigDecimal.valueOf(55000))
                .urlSeller("shopee.co.id")
                .username("Lophita")
                .stockUpdated(LocalDateTime.now())
                .createdTime(LocalDateTime.now())
                .build();
        StockDto stockDto = StockDto.builder()
                .id(stock.getId())
                .identifier(stock.getIdentifier())
                .name(stock.getName())
                .notes(stock.getNotes())
                .price(stock.getPrice())
                .urlSeller(stock.getUrlSeller())
                .username(stock.getUsername())
                .stockUpdated(stock.getStockUpdated())
                .createdTime(stock.getCreatedTime())
                .build();
        Mockito.when(stockRepository.insert(stock)).thenReturn(stock);
        StockDto result = stockService.save(stock);
        Mockito.verify(stockRepository, Mockito.times(1)).insert(stock);
        Assert.assertEquals(result.toString(), stockDto.toString());
    }

    @Test
    public void getAllStocksShouldReturnAllStocks(){
        Mockito.when(stockRepository.findAll()).thenReturn(stocks);
        List<Stock> results = stockService.findAll();
        Mockito.verify(stockRepository, Mockito.times(1)).findAll();
        Assert.assertEquals(results.size(), stocks.size());
    }
}
