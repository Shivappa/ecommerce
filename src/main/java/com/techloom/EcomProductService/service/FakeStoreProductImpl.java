package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductRequestDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {

        String getProductsURL = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductResponseDTO[]> productResponseArray =
                restTemplate.getForEntity(getProductsURL, ProductResponseDTO[].class);
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(ProductResponseDTO productResponseDTO : productResponseArray.getBody()) {
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return  productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String getProductURL = "https://fakestoreapi.com/products/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductResponseDTO> productResponse =
                restTemplate.getForEntity(getProductURL, ProductResponseDTO.class);
        return productResponse.getBody();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        String createProductURL = "https://fakestoreapi.com/products/";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductResponseDTO> response  =
                restTemplate.postForEntity(createProductURL, productRequestDTO, ProductResponseDTO.class);
        return response.getBody();
    }

    @Override
    public Product updateProductById(int id, Product updateProduct) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {

        String deleteProductURL = "https://fakestoreapi.com/products/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(deleteProductURL);
        return true;
    }
}
