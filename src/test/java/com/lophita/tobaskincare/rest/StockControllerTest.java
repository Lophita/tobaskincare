package com.lophita.tobaskincare.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.persistence.Stock;
import com.lophita.tobaskincare.service.DefaultStockService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@DisplayName("Stock Controller Test")
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultStockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<Stock> stockCaptor;

    private StockDto stockDto;

    @BeforeEach
    public void setup() {
        stockDto = StockDto.builder()
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
    }

    @Test
    @DisplayName("Add Stock when Valid then Returns 201")
    public void addStockWhenValidThenReturns201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(stockDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Add Stock when Valid Input then Maps to Service")
    public void addStockWhenValidInputThenMapsToService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(stockDto)));

        Mockito.verify(stockService, Mockito.times(1)).save(stockCaptor.capture());

        Stock value = stockCaptor.getValue();
        Assertions.assertEquals(objectMapper.writeValueAsString(stockDto), objectMapper.writeValueAsString(value));
    }

    @Test
    @DisplayName("Add Stock when Valid then Returns Stock Resource")
    public void addStockWhenValidThenReturnsStockResource() throws Exception {
        Mockito.when(stockService.save(Mockito.any(Stock.class))).thenReturn(stockDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(stockDto)))
                .andReturn();

        BaseResponse<StockDto> expectedResponse = new BaseResponse<>("SUCCESS", "Success", stockDto, null);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);
    }

    @Test
    @DisplayName("Add Stock when Not Valid then Returns 400")
    public void addStockWhenNotValidThenReturns400() throws Exception {
        stockDto = StockDto.builder().build();
        mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(stockDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Add Stock when Not Valid then Returns Errors")
    public void addStockWhenNotValidThenReturnsErrors() throws Exception {
        stockDto = StockDto.builder().build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(stockDto)))
                .andReturn();

        Map<String, String> errors = new HashMap<>();
        errors.put("identifier", "Identifier can not be empty");
        errors.put("name", "Name can not be empty");
        errors.put("username", "Username can not be empty");
        errors.put("price", "Price can not be null");
        BaseResponse<Object> expectedResponse = new BaseResponse<Object>("FAILED", "failed", null, errors);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        // cara ke-1:
        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);

        // cara ke-2:
        BaseResponse actualResult = objectMapper.readValue(actualResponseBody, BaseResponse.class);
        Assertions.assertTrue(new ReflectionEquals(expectedResponse).matches(actualResult));

        // cara ke-3: lombok - @EqualsAndHashCode
        Assertions.assertEquals(expectedResponse, actualResult);
    }

    @Disabled
    @Test
    @DisplayName("Add Stock when Not Valid Pattern then Returns Errors")
    public void addStockWhenNotValidPatternThenReturnsErrors() throws Exception {
        // todo check: handle if price input is alphabet
        String stockDtoContent = "{\"id\":\"this-id-add-stock-test\"," +
                "\"identifier\":\"SKU-SCRT-001-009\"," +
                "\"name\":\"Scarlett Whitening Lotion\"," +
                "\"stockUpdated\":\"2021-12-23 17:19:47\"," +
                "\"price\":\"text\"," +
                "\"notes\":\"This Product Will expired on July 19, 2024\"," +
                "\"urlSeller\":\"shopee.co.id\"," +
                "\"username\":\"Lophita 123\"," +
                "\"createdTime\":\"2021-12-23 17:19:47\"}";
        System.out.println(objectMapper.writeValueAsString(stockDto));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(stockDtoContent))
                .andReturn();

        Map<String, String> errors = new HashMap<>();
        errors.put("username", "Username must be alphabetic");
        BaseResponse<Object> expectedResponse = new BaseResponse<Object>("FAILED", "failed", null, errors);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        // cara ke-1:
        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);

        // cara ke-2:
        BaseResponse actualResult = objectMapper.readValue(actualResponseBody, BaseResponse.class);
        Assertions.assertTrue(new ReflectionEquals(expectedResponse).matches(actualResult));

        // cara ke-3: lombok - @EqualsAndHashCode
        Assertions.assertEquals(expectedResponse, actualResult);
    }

}
