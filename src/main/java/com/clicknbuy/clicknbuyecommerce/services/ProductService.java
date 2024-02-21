// ProductService.java
package com.clicknbuy.clicknbuyecommerce.services;


import com.clicknbuy.clicknbuyecommerce.dto.ProductRequestDto;

import java.util.List;

public interface ProductService {

   ProductRequestDto createProduct(ProductRequestDto productRequestDto);

    void deleteProduct(Long id);

    List<ProductRequestDto> getAllProducts();

    ProductRequestDto getProductById(Long id);

    void updateProduct(Long id, ProductRequestDto product);
}
