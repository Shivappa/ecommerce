package com.techloom.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponseDTO {
    private List<ProductResponseDTO> products;

    public ProductListResponseDTO() {
        this.products = new ArrayList<>(); // This will never endup in having a NullPointerException on the usage on service class
    }
}
