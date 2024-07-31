package com.example.mobile_space_order_system.dto;

import lombok.Data;

@Data
public class MobileSpaceCartDataResp {
    private Long cartId;
    private Long itemId;
    private String itemName;
    private String itemType;
    private int itemPrice;
    private int itemQty;
    private int itemTotal;
}
