package com.example.mobile_space_order_system.repository;

import com.example.mobile_space_order_system.entity.Cart;
import com.example.mobile_space_order_system.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
