package com.techloom.EcomProductService.client;

import com.techloom.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.techloom.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.techloom.EcomProductService.dto.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Wrapper on FakeStoreProduct APIs
 * */

@Component
public class FakeStoreAPIClient {
    private RestTemplateBuilder restTemplateBuilder;

    private String fakeStoreAPIURL;

    //Field injection
    @Value("${fakestore.api.path.product}")
    private String fakeStoreAPIPathProduct;

    //Constructor Injection of pathProductURL
    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder,
                              @Value("${fakestore.api.url}") String fakeStoreAPIURL) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIURL = fakeStoreAPIURL;
    }

    public FakeStoreProductResponseDTO createProduct(
            FakeStoreProductRequestDTO fakeStoreProductRequestDTO) {
        String createProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.postForEntity(
                        createProductURL,
                        fakeStoreProductRequestDTO,
                        FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public FakeStoreProductResponseDTO getProductById(int id) {
        String getProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(getProductURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public List<FakeStoreProductResponseDTO> getAllProducts() {
        String getProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray =
                restTemplate.getForEntity(getProductURL, FakeStoreProductResponseDTO[].class);
        return List.of(productResponseArray.getBody());
    }

    public void deleteProduct(int id) {
        String deleteProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(deleteProductURL);
    }
}
