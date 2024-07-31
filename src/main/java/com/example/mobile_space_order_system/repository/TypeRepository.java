package com.example.mobile_space_order_system.repository;

import com.example.mobile_space_order_system.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByTypeName(String name);
}
