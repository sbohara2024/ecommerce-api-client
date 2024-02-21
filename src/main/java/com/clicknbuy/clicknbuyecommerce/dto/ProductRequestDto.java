package com.clicknbuy.clicknbuyecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
    private Long id;
    private String name;
    private double price;
}
