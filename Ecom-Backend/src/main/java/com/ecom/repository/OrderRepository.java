package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
