package com.clicknbuy.clicknbuyecommerce.utils;

import com.clicknbuy.clicknbuyecommerce.dto.ProductRequestDto;
import com.clicknbuy.clicknbuyecommerce.entities.Product;
import com.clicknbuy.openapi.model.ProductRequest;
import com.clicknbuy.openapi.model.ProductResponse;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class ProductMapper {

    public static Product mapProductRequestDtoToProduct(ProductRequestDto productRequest) {
        if (productRequest == null) {
            return null;
        }
        Product product = new Product();
        product.setName(StringUtils.trimToNull(productRequest.getName()));
        product.setPrice(productRequest.getPrice());
        return product;
    }

    public static ProductRequestDto mapProductToProductRequestDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName(StringUtils.trimToNull(product.getName()));
        productRequestDto.setPrice(product.getPrice());
        productRequestDto.setId(product.getId());
        return productRequestDto;
    }
    public static ProductRequestDto mapProductRequestToProductRequestDto(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName(StringUtils.trimToNull(productRequest.getName()));
        productRequestDto.setPrice(productRequest.getPrice().doubleValue());
        return productRequestDto;
    }

    public static com.clicknbuy.openapi.model.ProductResponse mapProductDtoToProductResponse(ProductRequestDto productRequestDto) {
        com.clicknbuy.openapi.model.ProductResponse resp = new ProductResponse();
        resp.setName(StringUtils.trimToNull(productRequestDto.getName()));
        resp.setPrice(BigDecimal.valueOf(productRequestDto.getPrice()));
        resp.setId(Integer.parseInt(String.valueOf(productRequestDto.getId())));
        return resp;
    }
}
