package com.codewithchandu.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithchandu.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
