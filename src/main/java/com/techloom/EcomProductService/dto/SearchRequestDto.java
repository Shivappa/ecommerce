package com.techloom.EcomProductService.dto;

import com.techloom.EcomProductService.model.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String title;
    private int pageNumber;
    private int pageSize;
    //private String sortParam;
    private List<SortParam> sortParams;
}
