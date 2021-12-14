package com.lophita.tobaskincare.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.service.StockService;
import org.json.JSONObject;
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
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void testAddStock() throws Exception {
//        StockDto stockDto = new StockDto("string-coba-coba", "MB-001-121", "Maybelline - Blush On",
//                LocalDateTime.now(), BigDecimal.valueOf(65000), "Expired 2 Desember 2022",
//                "shopee.co.id","Lophita N", LocalDateTime.now());
        StockDto stockDto = StockDto.builder()
                .id("id-test-add-stock")
                .identifier("MB-001-012")
                .name("Maybelline - Blush On")
                .stockUpdated(LocalDateTime.now())
                .price(BigDecimal.valueOf(65000))
                .notes("Expired on 12 Des 2023")
                .urlSeller("shopee.co.id")
                .username("Lophita")
                .createdTime(LocalDateTime.now())
                .build();
        String stockDtoJson = "{\"createdTime\":\"2021-12-13 06:36:03\",\"identifier\":\"MBL-012-003\",\"name\":\"Maybeline compact powder\",\"notes\":\"expired on dec 2023\",\"price\":78000,\"stockUpdated\":\"2021-12-13 06:36:03\",\"urlSeller\":\"shopee.co.id\",\"username\":\"Lophita\"}";
//        StockDto stockDto1 = new ObjectMapper().readValue(stockDtoJson, StockDto.class);

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
        Mockito.when(stockService.save(Mockito.any(Stock.class))).thenReturn(stockDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(stockDtoJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(201, status);
//        Assert.assertEquals(stockDtoJson, result);
//        Mockito.verify(stockService.save(stock));
    }

}
