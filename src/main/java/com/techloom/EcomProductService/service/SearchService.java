package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.dto.GenericProductDto;
import com.techloom.EcomProductService.dto.SearchRequestDto;
import com.techloom.EcomProductService.model.Product;
import com.techloom.EcomProductService.model.SortParam;
import com.techloom.EcomProductService.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<GenericProductDto> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams) {
//        Sort sort = Sort.by("title").ascending()
//                .and(Sort.by("price").ascending())
//                .and(Sort.by("rating").descending())
//                .and(Sort.by("delivery_time").ascending());

        Sort sort = null;

        if (sortParams.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
        } else {
            sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
        }
        for (int i = 1; i < sortParams.size(); i++) {
            if (sortParams.get(i).getSortType().equals("ASC")) {
                sort.and(Sort.by(sortParams.get(i).getSortParamName()).ascending());
            } else {
                sort.and(Sort.by(sortParams.get(i).getSortParamName()).descending());
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        List<Product> products = productRepository.findAllByTitleContainingIgnoreCase(query, pageRequest);
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product : products) {
            genericProductDtos.add(GenericProductDto.from(product));
        }
        return genericProductDtos;
    }
}
