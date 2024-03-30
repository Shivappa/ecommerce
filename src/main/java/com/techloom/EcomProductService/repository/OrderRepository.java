package com.techloom.EcomProductService.repository;

import com.techloom.EcomProductService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}