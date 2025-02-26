package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.exception.ProductNotFoundException;
import com.techloom.EcomProductService.client.FakeStoreAPIClient;
import com.techloom.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.techloom.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductRequestDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

// static imports the method and we can use the method name directly
import static com.techloom.EcomProductService.mapper.ProductMapper.fakeProductResponseToProductResponse;
import static com.techloom.EcomProductService.mapper.ProductMapper.productRequestToFakeStoreRequest;
import static com.techloom.EcomProductService.util.ProductUtils.isNull;

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
                    fakeProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }
        return  productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
        if(isNull(fakeStoreProductResponseDTO)){
            throw new ProductNotFoundException("Product not found with id : " + id);
        }
        return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO =
                productRequestToFakeStoreRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductDTO =
                fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);

        return fakeProductResponseToProductResponse(fakeStoreProductDTO);
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

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        return null;
    }
}
