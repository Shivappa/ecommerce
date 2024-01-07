package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.Exception.ProductNotFoudException;
import com.techloom.EcomProductService.client.FakeStoreAPIClient;
import com.techloom.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.techloom.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductRequestDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// static imports the method and we can use the method name directly
import static com.techloom.EcomProductService.mapper.ProductMapper.FakeStoreResponseToProductResponse;
import static com.techloom.EcomProductService.mapper.ProductMapper.productRequestToFakeStoreRequest;
import static com.techloom.EcomProductService.util.ProductUtils.isNull;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreAPIClient fakeStoreAPIClient;

    public FakeStoreProductImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
         List<FakeStoreProductResponseDTO> productResponseArray =
                 fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO : productResponseArray) {
            productListResponseDTO.getProducts().add(
                    FakeStoreResponseToProductResponse(fakeStoreProductResponseDTO));
        }
        return  productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoudException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO =
                fakeStoreAPIClient.getProductById(id);
        if (isNull(fakeStoreProductResponseDTO)){
            throw new ProductNotFoudException("Product not found for id:"+id);
        }
        return FakeStoreResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO =
                productRequestToFakeStoreRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductDTO =
                fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);

        return FakeStoreResponseToProductResponse(fakeStoreProductDTO);
    }


    @Override
    public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }


    @Override
    public Product updateProductById(int id, Product updateProduct) {
        return null;
    }
}
