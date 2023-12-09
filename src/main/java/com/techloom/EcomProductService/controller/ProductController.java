package com.techloom.EcomProductService.controller;

import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {


    @Autowired
    @Qualifier("fakeStoreProductService")
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){

        ProductListResponseDTO productListResponseDTO = productService.getAllProducts();
        return ResponseEntity.ok(productListResponseDTO);
        /*
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(1);
        productResponseDTO.setTitle("iPhone 15");
        productResponseDTO.setPrice(150000);
        productResponseDTO.setImage("www.google.com/images/iphone");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setDescription("A good phone to buy");

        ProductResponseDTO productResponseDTO1 = new ProductResponseDTO();
        productResponseDTO1.setId(2);
        productResponseDTO1.setTitle("iPhone 15 Pro");
        productResponseDTO1.setPrice(250000);
        productResponseDTO1.setImage("www.google.com/images/iphone15pro");
        productResponseDTO1.setCategory("Electronics");
        productResponseDTO1.setDescription("A very expensive phone");

        List<ProductResponseDTO> productResponseDTOList = Arrays.asList(productResponseDTO, productResponseDTO1);
        return ResponseEntity.ok(productResponseDTOList);

         */

    }

    @GetMapping("/products/1")
    public ResponseEntity getProductById() {
        ProductResponseDTO productResponseDTO = productService.getProductById(1);
        return ResponseEntity.ok(productResponseDTO);

    }


}
