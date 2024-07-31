package com.example.mobile_space_order_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class MobileSpaceCartResp {
    private long totalElements;
    private long totalPages;
    private List<MobileSpaceCartDataResp> data;
}
