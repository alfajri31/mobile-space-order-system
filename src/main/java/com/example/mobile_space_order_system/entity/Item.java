package com.example.mobile_space_order_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mobile_space_item")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_price")
    private int itemPrice;
    @Column(name = "type_id")
    private Long typeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id",insertable = false,updatable = false)
    private Type type;
    @ManyToMany(mappedBy = "items", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Cart> carts = new HashSet<>();

}
