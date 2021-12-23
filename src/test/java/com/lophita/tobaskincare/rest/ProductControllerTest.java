package com.lophita.tobaskincare.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.persistence.Type;
import com.lophita.tobaskincare.service.DefaultProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import java.util.HashMap;
import java.util.Map;

@DisplayName("Product Controller Test")
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DefaultProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<Product> productCaptor;

    private ProductDto productDto;

    @BeforeEach
    public void setup() {
        productDto = ProductDto.builder()
                .id("id-add-product-test")
                .identifier("MKO-001-331")
                .name("MakeOver Lipstik shade RED")
                .type(Type.MAKEUP.toString())
                .notes("lipstik matte tahan 16 jam")
                .build();
    }

    @Test
    @DisplayName("Add Product when Valid Input then Returns 201")
    public void addProductWhenValidThenReturns201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Add Product when Valid Input then Maps to Service")
    public void addProductWhenValidThenMapsToService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)));

        Mockito.verify(productService, Mockito.times(1)).save(productCaptor.capture());

        Product value = productCaptor.getValue();
        Assertions.assertEquals(objectMapper.writeValueAsString(productDto), objectMapper.writeValueAsString(value));
    }

    @Test
    @DisplayName("Add Product when Valid then Returns Product Resource")
    public void addProductWhenValidThenReturnsProductResource() throws Exception {
        Mockito.when(productService.save(Mockito.any(Product.class))).thenReturn(productDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andReturn();

        BaseResponse<ProductDto> expectedResponse = new BaseResponse<>("SUCCESS", "Success", productDto, null);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);
    }

    @Test
    @DisplayName("Add Product when Not Valid then Returns 400")
    public void addProductWhenNotValidThenReturns400() throws Exception {
        productDto = ProductDto.builder().build();
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Add Product when Not Valid then Returns Errors")
    public void addProductWhenNotValidThenReturnsErrors() throws Exception {
        productDto = ProductDto.builder().build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andReturn();

        Map<String, String> errors = new HashMap<>();
        errors.put("identifier", "Identifier can not be empty");
        errors.put("name", "Name can not be empty");
        errors.put("type", "Type can not be empty");
        BaseResponse<Object> expectedResponse = new BaseResponse<Object>("FAILED", "failed", null, errors);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        // cara ke-1:
        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);

        // cara ke-2:
        BaseResponse actualResult = objectMapper.readValue(actualResponseBody, BaseResponse.class);
        Assertions.assertTrue(new ReflectionEquals(expectedResponse).matches(actualResult));
    }
}
