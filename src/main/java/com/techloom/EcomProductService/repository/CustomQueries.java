package com.techloom.EcomProductService.repository;

public interface CustomQueries {
    String FIND_PRODUCT_BY_TITLE = "select * from product where title like :title and id = :id";
}
