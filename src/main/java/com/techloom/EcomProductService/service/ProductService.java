package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.Exception.ProductNotFoudException;
import com.techloom.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.techloom.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductRequestDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.model.Product;

import java.util.List;

public interface ProductService {
    ProductListResponseDTO getAllProducts();
    ProductResponseDTO getProductById(int id) throws ProductNotFoudException;
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    Product updateProductById(int id, Product updateProduct);
    boolean deleteProduct(int id);

}
