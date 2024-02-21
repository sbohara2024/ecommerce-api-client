package com.clicknbuy.clicknbuyecommerce.controller;

import com.clicknbuy.clicknbuyecommerce.dto.ProductRequestDto;
import com.clicknbuy.clicknbuyecommerce.exceptions.BadRequestException;
import com.clicknbuy.clicknbuyecommerce.services.ProductService;
import com.clicknbuy.clicknbuyecommerce.utils.ProductMapper;
import com.clicknbuy.openapi.ApiApi;
import com.clicknbuy.openapi.model.ProductRequest;
import com.clicknbuy.openapi.model.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ApiController implements ApiApi {

    private final ProductService productService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> addProduct(ProductRequest productRequest) {
        if (productRequest.getName() == null) {
            throw new BadRequestException("Name is mandatory field to create product");
        } else {
            ProductRequestDto productRequestDto = ProductMapper.mapProductRequestToProductRequestDto(productRequest);
            productRequestDto = productService.createProduct(productRequestDto);
            com.clicknbuy.openapi.model.ProductResponse productResponse = ProductMapper.mapProductDtoToProductResponse(productRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
        }
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Integer id) {
        productService.deleteProduct(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductRequestDto> productRequestDtoList = productService.getAllProducts();
        List<com.clicknbuy.openapi.model.ProductResponse> productResponses = productRequestDtoList.stream()
                .map(ProductMapper::mapProductDtoToProductResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(Integer id, ProductRequest productRequest) {
        ProductRequestDto productRequestDto = ProductMapper.mapProductRequestToProductRequestDto(productRequest);
        productService.updateProduct(Long.valueOf(id), productRequestDto);
        return getProductById(id);
    }

    @Override
    public ResponseEntity<com.clicknbuy.openapi.model.ProductResponse> getProductById(Integer id) {
        ProductRequestDto productRequestDto = productService.getProductById(Long.valueOf(id));
      if(productRequestDto !=null) {
          com.clicknbuy.openapi.model.ProductResponse productResponse = ProductMapper.mapProductDtoToProductResponse(productRequestDto);
          if (productRequestDto == null) {
              return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(productResponse);
      }
      return null;
    }
}
