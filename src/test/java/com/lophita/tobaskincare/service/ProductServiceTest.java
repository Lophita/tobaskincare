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

}
