package com.example.mobile_space_order_system.controller.api;

import com.example.mobile_space_order_system.dto.*;
import com.example.mobile_space_order_system.entity.Cart;
import com.example.mobile_space_order_system.service.MobileSpaceOrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MobileSpaceOrderController {


    private final MobileSpaceOrderService mobileSpaceOrderService;

    @PostMapping("cart")
    public ResponseEntity<MobileSpaceCartReq> addToCart(@RequestBody MobileSpaceCartReq cart) {
        mobileSpaceOrderService.addToCart(cart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("page-cart")
    public ResponseEntity<MobileSpaceCartResp> pageCart(@RequestBody PaginationReq pageRequest) {
        return new ResponseEntity<>(mobileSpaceOrderService.pageCart(pageRequest),HttpStatus.CREATED);
    }

    @PostMapping("update-qty")
    public ResponseEntity<UpdateQtyReq> updateQty(@RequestBody UpdateQtyReq updateQtyReq) {
        mobileSpaceOrderService.updateQty(updateQtyReq);
        return new ResponseEntity<>(updateQtyReq, HttpStatus.OK);
    }

    @PostMapping("order")
    public ResponseEntity<MobileSpaceOrder> placeOrder(@RequestBody MobileSpaceOrder order) {
        mobileSpaceOrderService.placeOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("item")
    public ResponseEntity<MobileSpaceItemResp> listItemByType(@RequestBody MobileSpaceItemReq itemReq) {
        return new ResponseEntity<>(mobileSpaceOrderService.itemReqByType(itemReq), HttpStatus.OK);
    }

    @PostMapping("type")
    public ResponseEntity<MobileSpaceTypeResp> listType() {
        return new ResponseEntity<>( mobileSpaceOrderService.listType(), HttpStatus.OK);
    }
}
