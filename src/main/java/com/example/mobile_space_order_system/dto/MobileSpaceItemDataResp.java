package com.example.mobile_space_order_system.dto;

import lombok.Data;

@Data
public class MobileSpaceItemDataResp {
    private Long typeId;
    private Long itemId;
    private String itemName;
    private int itemPrice;
}
