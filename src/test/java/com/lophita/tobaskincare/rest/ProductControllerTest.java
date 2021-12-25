package com.lophita.tobaskincare.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.persistence.Type;
import com.lophita.tobaskincare.service.DefaultProductService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<ProductDto> productDtoList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        productDto = ProductDto.builder()
                .id("id-add-product-test")
                .identifier("MKO-001-331")
                .name("MakeOver Lipstik shade RED")
                .type(Type.MAKEUP.toString())
                .notes("lipstik matte tahan 16 jam")
                .build();

        productDtoList.add(ProductDto.builder()
                .id("1")
                .identifier("SKU-120-001")
                .name("Compact Powder Whitening")
                .type(Type.MAKEUP.toString())
                .notes("expired 31 dec 2025")
                .build());
        productDtoList.add(ProductDto.builder()
                .id("2")
                .identifier("SKU-120-002")
                .name("Scarlett Whitening")
                .type(Type.SKINCARE.toString())
                .notes("expired 31 dec 2024")
                .build());
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

    @Test
    @DisplayName("Get All Products then Return All Products")
    public void getAllProductsThenReturnAllProducts() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(productDtoList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/product"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(productService, Mockito.times(1)).findAll();

        BaseResponse<List<ProductDto>> expectedResponse = new BaseResponse<>("SUCCESS", "Success", productDtoList, null);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);
    }

    @Disabled
    @Test
    @DisplayName("Get Product By Id when Valid then Return Product")
    public void getProductByIdThenReturnProduct() throws Exception {
        Mockito.when(productService.findById(productDto.getId())).thenReturn(productDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/product/" + productDto.getId())).andReturn();

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(productService, Mockito.times(1)).findById(stringCaptor.capture());

        BaseResponse<ProductDto> expectedResponse = new BaseResponse<>("SUCCESS", "Success", productDto, null);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);
    }

    @Disabled
    @Test
    @DisplayName("Get Product By Id when Invalid then Return Product Not Found")
    public void getProductByIdButProductNotFound() throws Exception {

    }
}
