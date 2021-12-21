package com.lophita.tobaskincare.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.persistence.Type;
import com.lophita.tobaskincare.service.DefaultProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
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

    @Test
    public void whenValidInputThenReturns201() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .id("id-add-product-test")
                .identifier("MKO-001-331")
                .name("MakeOver Lipstik shade RED")
                .type(Type.MAKEUP.toString())
                .notes("lipstik matte tahan 16 jam")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void whenNotValidInputThenReturns400() throws Exception {
        ProductDto productDto = ProductDto.builder().build();
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenValidInputThenMapsToService() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .id("id-add-product-test")
                .name("MakeOver Lipstik shade RED")
                .identifier("MKO-001-331")
                .type(Type.MAKEUP.toString())
                .notes("lipstik matte tahan 16 jam")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)));

        Mockito.verify(productService, Mockito.times(1)).save(productCaptor.capture());

        Product value = productCaptor.getValue();
        Assert.assertEquals(productDto.getId(), value.getId());
        Assert.assertEquals(productDto.getName(), value.getName());
        Assert.assertEquals(productDto.getIdentifier(), value.getIdentifier());
        Assert.assertEquals(productDto.getType(), value.getType());
        Assert.assertEquals(productDto.getNotes(), value.getNotes());
    }

    @Test
    public void whenValidInputThenReturnsProductResource() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .id("id-add-product-test")
                .name("MakeOver Lipstik shade RED")
                .identifier("MKO-001-331")
                .type(Type.MAKEUP.toString())
                .notes("lipstik matte tahan 16 jam")
                .build();

        Mockito.when(productService.save(Mockito.any(Product.class))).thenReturn(productDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andReturn();

        BaseResponse<ProductDto> expectedResponse = new BaseResponse<>("SUCCESS", "Success", productDto, null);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);
    }

    @Test
    public void whenNotValidInputThenReturns400AndErrorResult() throws Exception {
        ProductDto productDto = ProductDto.builder().build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Map<String, String> errors = new HashMap<>();
        errors.put("identifier", "Identifier can not be empty");
        errors.put("name", "Name can not be empty");
        errors.put("type", "Type can not be empty");
        BaseResponse<Object> expectedResponse = new BaseResponse<Object>("FAILED", "failed", null, errors);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        // cara ke-1:
//        Assert.assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponseBody);

        // cara ke-2:
        BaseResponse actualResult = objectMapper.readValue(actualResponseBody, BaseResponse.class);
        Assert.assertTrue(new ReflectionEquals(expectedResponse).matches(actualResult));
    }
}
