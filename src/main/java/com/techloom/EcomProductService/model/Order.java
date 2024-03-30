package com.techloom.EcomProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ecom_order")
public class Order extends BaseModel {

    private double price;

    @ManyToMany
    private List<Product> products;

    /*
        Product     Order
            1       M
            M       1

            M:M
     */
}
