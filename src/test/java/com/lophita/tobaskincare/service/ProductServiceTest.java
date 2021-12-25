package com.lophita.tobaskincare.service;

import com.lophita.tobaskincare.dto.ProductDto;
import com.lophita.tobaskincare.persistence.Product;
import com.lophita.tobaskincare.persistence.Type;
import com.lophita.tobaskincare.persistence.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DisplayName("Product Service Test")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DefaultProductService productService;

    @Test
    @DisplayName("Given Product to Add should Return Added Product")
    public void givenProductToAddShouldReturnAddedProduct() {
        Product product = Product.builder()
                .id("id-add-product-test")
                .name("Bedak Compact Powder")
                .notes("Apa aja boleh")
                .type(Type.MAKEUP.toString())
                .identifier("WRD-112-050")
                .build();
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .notes(product.getNotes())
                .type(product.getType())
                .identifier(product.getIdentifier())
                .build();
        Mockito.when(productRepository.insert(product)).thenReturn(product);
        ProductDto result = productService.save(product);
        Mockito.verify(productRepository, Mockito.times(1)).insert(product);
        Assertions.assertEquals(productDto, result);
        Assertions.assertEquals(productDto.hashCode(), result.hashCode());
    }

    @Test
    @DisplayName("Should Return All Products")
    public void shouldReturnAllProducts(){
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .id("1")
                .identifier("SKU-120-001")
                .name("Compact Powder Whitening")
                .type(Type.MAKEUP.toString())
                .notes("expired 31 dec 2025")
                .build());
        products.add(Product.builder()
                .id("2")
                .identifier("SKU-120-002")
                .name("Scarlett Whitening")
                .type(Type.SKINCARE.toString())
                .notes("expired 31 dec 2024")
                .build());

        List<ProductDto> productDtoList = products.stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .identifier(product.getIdentifier())
                        .type(product.getType())
                        .notes(product.getNotes())
                        .build())
                .collect(Collectors.toList());

        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<ProductDto> result = productService.findAll();
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(productDtoList, result);
        Assertions.assertEquals(productDtoList.hashCode(), result.hashCode());
    }

    @Test
    @DisplayName("Given Valid Id should Return Product")
    public void givenIdShouldReturnProduct() throws Exception {

    }

}
