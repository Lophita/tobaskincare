package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.BaseResponse;
import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public BaseResponse<List<ProductDto>> getAllProducts(){
        List<ProductDto> result = productService.findAll();
        BaseResponse<List<ProductDto>> response = new BaseResponse<>("SUCCESS", "Success", result, null);
        return response;
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

    @GetMapping(value = "/{id}")
    public BaseResponse<ProductDto> getProduct(@PathVariable String id){
        ProductDto result = productService.findById(id);
        BaseResponse<ProductDto> response = new BaseResponse<>("SUCCESS", "Success", result, null);
        return response;
    }
}
