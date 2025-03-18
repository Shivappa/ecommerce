package com.techloom.EcomProductService.repository;

import com.techloom.EcomProductService.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitle(String title);
    Product findByTitleAndDescription(String title, String description); // select * from Product where title = ? and description = ?
    Product findByTitleOrDescription(String title, String description); // select * from Product where title = ? or description = ?

    @Query(value = CustomQueries.FIND_PRODUCT_BY_TITLE, nativeQuery = true)
    Product findProductByTitleLike(String title);

    @Query(value = "select * from Products", nativeQuery = true)
    Product findAllProducts(String title, UUID id);

    @Override
    List<Product> findAll(); // Get all the products from the Product table.

    List<Product> findAllByTitle(String title);

    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    /*
    Select * from products where lower(title) = "iphone"
    //offset and limit.
     */


    List<Product> findAllByTitleAndDescription(String title, String desc);

    @Override
    <S extends Product> List<S> findAll(Example<S> example);

    List<Product> findAllByPrice_ValueLessThan(Integer x);


    //@Query(value = "select * from product where id = 1", nativeQuery = true)
    List<Product> findAllByPrice_ValueBetween(Integer x, Integer y);

    // custom SQL queries can also be done in JPA
}
