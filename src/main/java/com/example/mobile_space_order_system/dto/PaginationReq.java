package com.example.mobile_space_order_system.dto;

import lombok.Data;

@Data
public class PaginationReq {
    private int pageSize;
    private int pageNumber;
}
