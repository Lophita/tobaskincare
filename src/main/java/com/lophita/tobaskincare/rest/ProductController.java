package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
