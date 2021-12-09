package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getAllProduct(){
        List<Product> list = productService.findAllProduct();
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
}
