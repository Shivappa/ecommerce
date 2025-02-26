package com.techloom.EcomProductService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techloom.EcomProductService.client.UserServiceClient;
import com.techloom.EcomProductService.dto.JwtPayloadDTO;
import com.techloom.EcomProductService.dto.SessionStatus;
import com.techloom.EcomProductService.dto.ValidateTokenDTO;
import com.techloom.EcomProductService.exception.InvalidTokenException;
import com.techloom.EcomProductService.exception.ProductNotFoundException;
import com.techloom.EcomProductService.dto.ProductListResponseDTO;
import com.techloom.EcomProductService.dto.ProductRequestDTO;
import com.techloom.EcomProductService.dto.ProductResponseDTO;
import com.techloom.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class ProductController {

    private final ProductService productService;
    private final UserServiceClient userServiceClient;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService, UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }

    /** Field Injection
    @Autowired
    @Qualifier("fakeStoreProductService")
    private ProductService productService;
    ...*/

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){

        ProductListResponseDTO productListResponseDTO = productService.getAllProducts();
        return ResponseEntity.ok(productListResponseDTO);
        /**
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

         ...*/

    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id) throws ProductNotFoundException {
        ProductResponseDTO productResponseDTO = productService.getProductById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/products/title/{title}")
    public ResponseEntity getProductFromTitle(@PathVariable("title") String title, @RequestHeader("token") String token) throws ProductNotFoundException {
        /*
        ProductResponseDTO p1 =  new ProductResponseDTO();
        p1.setId(1);
        p1.setTitle("Iphone 15 pro");
        p1.setPrice(150000);
        p1.setImage("www.google.com/images/iphone");
        p1.setDescription("Kafi Mehnga phone");
        p1.setCategory("Electronics");

        ProductResponseDTO p2 =  new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook Pro");
        p2.setPrice(250000);
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Kafi Mehnga laptop");
        p2.setCategory("Electronics");

        List<ProductResponseDTO> products = Arrays.asList(p1, p2);
        return ResponseEntity.ok(products);
        */
        ProductResponseDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO =
                productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id) {
        boolean response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    private void validateUser(String token) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper mapper = new ObjectMapper();
        JwtPayloadDTO jwtPayload = mapper.readValue(payload, JwtPayloadDTO.class);
        int userId = jwtPayload.getUserId();
        ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(userId, token);
        String result = userServiceClient.validateToken(validateTokenDTO);
        if(!result.contains(SessionStatus.ACTIVE.name())){
            throw new InvalidTokenException("Token is not valid");
        }
    }


}
