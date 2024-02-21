package com.clicknbuy.clicknbuyecommerce;

import com.clicknbuy.clicknbuyecommerce.controller.ApiController;
import com.clicknbuy.clicknbuyecommerce.dto.ProductRequestDto;
import com.clicknbuy.clicknbuyecommerce.exceptions.BadRequestException;
import com.clicknbuy.clicknbuyecommerce.services.ProductService;
import com.clicknbuy.openapi.model.ProductRequest;
import com.clicknbuy.openapi.model.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class ApiControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct_ValidProduct_ReturnsCreatedResponse() {
        ProductRequest validProductRequest = new ProductRequest();
        validProductRequest.setName("Test Product");
        validProductRequest.setPrice(new BigDecimal("120.00"));
        ProductRequestDto mappedDto = new ProductRequestDto();
        mappedDto.setName("Test Product");
        mappedDto.setId(1L);
        mappedDto.setPrice(new BigDecimal("120.00").doubleValue());
        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setName("Test Product");
        expectedResponse.setPrice(new BigDecimal("120.00"));
        when(productService.createProduct(any(ProductRequestDto.class))).thenReturn(mappedDto);
        ResponseEntity<ProductResponse> response = apiController.addProduct(validProductRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.getName(), response.getBody().getName());
    }

    @Test
    void addProduct_NullName_ThrowsBadRequestException() {
        ProductRequest invalidProductRequest = new ProductRequest();
        assertThrows(BadRequestException.class, () -> apiController.addProduct(invalidProductRequest));
    }

    @Test
    void deleteProduct_ValidId_ReturnsNoContentResponse() {
        int validProductId = 1;
        doNothing().when(productService).deleteProduct(Mockito.anyLong());
        ResponseEntity<Void> response = apiController.deleteProduct(validProductId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllProducts_ReturnsListOfProducts() {
        List<ProductRequestDto> productDtoList = Arrays.asList(new ProductRequestDto(1L, "p1", 10.00d),
                new ProductRequestDto(2L, "p2", 20.00d));
        when(productService.getAllProducts()).thenReturn(productDtoList);
        ResponseEntity<List<ProductResponse>> response = apiController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productDtoList.size(), response.getBody().size());
    }

    @Test
    void getProductById_ValidId_ReturnsProductResponse() {
        int validProductId = 1;
        ProductRequestDto productDto = new ProductRequestDto();
        productDto.setName("Test Product");
        productDto.setId(1L);
        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setName("Test Product");
        expectedResponse.setId(1);
        when(productService.getProductById(Mockito.anyLong())).thenReturn(productDto);
        ResponseEntity<ProductResponse> response = apiController.getProductById(validProductId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.getName(), response.getBody().getName());
    }

    @Test
    void getProductById_InvalidId_ReturnsNotFoundResponse() {
        int invalidProductId = 999;
        when(productService.getProductById(Long.valueOf(invalidProductId))).thenReturn(null);
        ResponseEntity<ProductResponse> response = apiController.getProductById(invalidProductId);
        assertEquals(null, response);
    }
}
