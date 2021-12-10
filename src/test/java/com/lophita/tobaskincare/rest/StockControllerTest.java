package com.lophita.tobaskincare.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.service.StockService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    public void testAddStock() throws Exception {
        StockDto stockDto = new StockDto("string-coba-coba", "MB-001-121", "Maybelline - Blush On",
                LocalDateTime.now(), BigDecimal.valueOf(65000), "Expired 2 Desember 2022",
                "shopee.co.id","Lophita N", LocalDateTime.now());
        String stockDtoJson = new ObjectMapper().writeValueAsString(stockDto);
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
        Mockito.when(stockService.save(Mockito.any(Stock.class))).thenReturn(stock);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(stockDtoJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(201, status);
        Assert.assertEquals(stockDtoJson, result);
    }
}
