package com.example.mobile_space_order_system.dto;

import lombok.Data;

@Data
public class MobileSpaceCartReq {
    private Long itemId;
    private String itemName;
    private int itemQty;
    private int itemPrice;
}
