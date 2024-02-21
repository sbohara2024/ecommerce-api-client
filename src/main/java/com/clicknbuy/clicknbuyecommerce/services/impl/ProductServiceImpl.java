// ProductServiceImpl.java
package com.clicknbuy.clicknbuyecommerce.services.impl;

import com.clicknbuy.clicknbuyecommerce.dto.ProductRequestDto;
import com.clicknbuy.clicknbuyecommerce.entities.Product;
import com.clicknbuy.clicknbuyecommerce.repositories.ProductRepository;
import com.clicknbuy.clicknbuyecommerce.services.ProductService;
import com.clicknbuy.clicknbuyecommerce.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductRequestDto createProduct(ProductRequestDto productRequestDto) {
        Product product = ProductMapper.mapProductRequestDtoToProduct(productRequestDto);
        Product productSaved = productRepository.save(product);
        return ProductMapper.mapProductToProductRequestDto(productSaved);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductRequestDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductRequestDto> productResponses = products.stream()
                .map(ProductMapper::mapProductToProductRequestDto)
                .collect(Collectors.toList());
        return productResponses;
    }

    @Override
    public ProductRequestDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return ProductMapper.mapProductToProductRequestDto(product);
        }
        return null;
    }


    @Override
    public void updateProduct(Long id, ProductRequestDto product) {
        productRepository.findById(id).ifPresent(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
        });
    }


}
