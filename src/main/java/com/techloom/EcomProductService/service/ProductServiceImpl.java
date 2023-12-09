package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductListResponseDTO getAllProducts() {
        return null;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProductById(int id, Product updateProduct) {
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        return null;
    }
}
