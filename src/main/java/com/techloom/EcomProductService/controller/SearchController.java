package com.techloom.EcomProductService.controller;

import com.techloom.EcomProductService.dto.GenericProductDto;
import com.techloom.EcomProductService.dto.SearchRequestDto;
import com.techloom.EcomProductService.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto) {
        List<GenericProductDto> genericProductDtos = searchService.searchProducts(requestDto.getTitle(),
                requestDto.getPageNumber(),
                requestDto.getPageSize(),
                requestDto.getSortParams());

        Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(
                genericProductDtos
        );

        return genericProductDtoPage;
    }
}
