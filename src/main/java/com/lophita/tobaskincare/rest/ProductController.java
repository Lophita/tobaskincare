package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getAllProducts(){
        List<Product> list = productService.findAll();
        List<ProductDto> productDtoList = list.stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .identifier(product.getIdentifier())
                        .type(product.getType())
                        .notes(product.getNotes())
                        .build())
                .collect(Collectors.toList());
        return productDtoList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BaseResponse<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto){
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .identifier(productDto.getIdentifier())
                .notes(productDto.getNotes())
                .type(productDto.getType())
                .build();
        ProductDto result = productService.save(product);
        BaseResponse<ProductDto> response = new BaseResponse<>("SUCCESS", "Success", result, null);
        return response;
    }

//    @GetMapping(value = "/{id}")
//    public ProductDto getProduct(@PathVariable String id){
//        Product product = productService.findProductById(id);
//        System.out.println(product.toString());
//        ProductDto productDto = ProductDto.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .identifier(product.getIdentifier())
//                .type(product.getType())
//                .notes(product.getNotes())
//                .build();
//        return productDto;
//    }
}
