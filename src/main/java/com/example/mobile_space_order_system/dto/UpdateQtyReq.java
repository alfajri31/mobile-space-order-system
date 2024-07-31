package com.example.mobile_space_order_system.dto;

import lombok.Data;

@Data
public class UpdateQtyReq {
    private Long cartId;
    private int qty;
}
