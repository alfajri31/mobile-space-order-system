package com.example.mobile_space_order_system.repository;

import com.example.mobile_space_order_system.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findAllByTypeId(Long typeId);
}
