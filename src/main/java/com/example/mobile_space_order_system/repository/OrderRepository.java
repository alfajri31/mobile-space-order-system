package com.example.mobile_space_order_system.repository;

import com.example.mobile_space_order_system.entity.Cart;
import com.example.mobile_space_order_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
