package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.model.Product;

import java.util.List;

public interface ProductService {
    ProductListResponseDTO getAllProducts();
    ProductResponseDTO getProductById(int id);
    Product createProduct(Product product);
    Product updateProductById(int id, Product updateProduct);
    Product deleteProduct(int id);

}
