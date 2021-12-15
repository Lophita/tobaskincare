package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void testAddStockSuccess() throws Exception {
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
        Mockito.when(stockService.save(stock)).thenReturn(stockDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(stockDto.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(stockService, Mockito.times(1)).save(stock);
    }

}
