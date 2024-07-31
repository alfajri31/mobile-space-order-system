package com.example.mobile_space_order_system.service;

import com.example.mobile_space_order_system.dto.*;
import com.example.mobile_space_order_system.entity.Cart;
import com.example.mobile_space_order_system.entity.Item;
import com.example.mobile_space_order_system.entity.Order;
import com.example.mobile_space_order_system.entity.Type;
import com.example.mobile_space_order_system.repository.CartRepository;
import com.example.mobile_space_order_system.repository.ItemRepository;
import com.example.mobile_space_order_system.repository.OrderRepository;
import com.example.mobile_space_order_system.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MobileSpaceOrderService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void addToCart(MobileSpaceCartReq cart) {
        Cart newCart = new Cart();
        newCart.setItemId(cart.getItemId());
        Optional<Item> item = itemRepository.findById(cart.getItemId());
        if(item.isPresent()) {
            newCart.setItemQty(cart.getItemQty());
            newCart.setItemTotalPrice(item.get().getItemPrice() * cart.getItemQty());
            cartRepository.save(newCart);
        }
    }

    public MobileSpaceCartResp pageCart(PaginationReq pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        Page<Cart> cartPage = cartRepository.findAll(pageable);
        List<MobileSpaceCartDataResp> cartResps = getMobileSpaceCartDataResps(cartPage);
        MobileSpaceCartResp mobileSpaceCartResp = new MobileSpaceCartResp();
        mobileSpaceCartResp.setData(cartResps);
        mobileSpaceCartResp.setTotalElements(cartPage.getTotalElements());
        mobileSpaceCartResp.setTotalPages(cartPage.getTotalPages());
        return mobileSpaceCartResp;
    }

    private static List<MobileSpaceCartDataResp> getMobileSpaceCartDataResps(Page<Cart> cartPage) {
        List<MobileSpaceCartDataResp> cartResps = new ArrayList<>();
        for (Cart cart : cartPage.getContent()) {
            MobileSpaceCartDataResp cartResp = new MobileSpaceCartDataResp();
            cartResp.setCartId(cart.getId());
            cartResp.setItemId(cart.getItemId());
            cartResp.setItemName(cart.getItem().getItemName());
            cartResp.setItemType(cart.getItem().getType().getTypeName());
            cartResp.setItemQty(cart.getItemQty());
            cartResp.setItemTotal(cart.getItemTotalPrice());
            cartResps.add(cartResp);
        }
        return cartResps;
    }

    public void updateQty(UpdateQtyReq updateQtyReq) {
        Cart cart = cartRepository.findById(updateQtyReq.getCartId()).orElse(null);
        if (cart!= null) {
            cart.setItemQty(updateQtyReq.getQty());
            cart.setItemTotalPrice(
                    cart.getItems().stream().findFirst().get().getItemPrice() * cart.getItemQty()
            );
            cartRepository.save(cart);
        }
    }

    public void placeOrder(MobileSpaceOrder order) {
        List<Order> orders = new ArrayList<>();
        order.getCartIds().stream().forEach(id -> {
            Order newOrder = new Order();
            newOrder.setCartId(id);
            orders.add(newOrder);
        });
        List<Order> orderPlaced = orderRepository.saveAll(orders);
        orderPlaced.forEach(thisOrder -> {
            cartRepository.deleteById(thisOrder.getCartId());
        });
    }

    public MobileSpaceItemResp itemReqByType(MobileSpaceItemReq itemReq) {
        List<Item> items = itemRepository.findAllByTypeId(itemReq.getTypeId());
        List<MobileSpaceItemDataResp> itemResps = new ArrayList<>();
        for (Item item : items) {
            MobileSpaceItemDataResp itemResp = new MobileSpaceItemDataResp();
            itemResp.setItemId(item.getId());
            itemResp.setTypeId(item.getTypeId());
            itemResp.setItemName(item.getItemName());
            itemResp.setItemPrice(item.getItemPrice());
            itemResps.add(itemResp);
        }
        MobileSpaceItemResp mobileSpaceItemResp = new MobileSpaceItemResp();
        mobileSpaceItemResp.setData(itemResps);
        return mobileSpaceItemResp;
    }

    public MobileSpaceTypeResp listType() {
        List<Type> types = typeRepository.findAll();
        List<MobileSpaceTypeDataResp> typeResps = new ArrayList<>();
        for (Type type : types) {
            MobileSpaceTypeDataResp typeResp = new MobileSpaceTypeDataResp();
            typeResp.setTypeId(type.getId());
            typeResp.setTypeName(type.getTypeName());
            typeResps.add(typeResp);
        }
        MobileSpaceTypeResp mobileSpaceTypeResp = new MobileSpaceTypeResp();
        mobileSpaceTypeResp.setData(typeResps);
        return mobileSpaceTypeResp;
    }
}
