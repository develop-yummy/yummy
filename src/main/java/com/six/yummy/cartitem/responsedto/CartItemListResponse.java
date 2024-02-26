package com.six.yummy.cartitem.responsedto;

import lombok.Getter;

@Getter
public class CartItemListResponse {

    private Long menuId;
    private boolean state;
    private int count;
    private int totalPrice;


    public CartItemListResponse(Long menuId, boolean state, int count, int totalPrice) {
        this.menuId = menuId;
        this.state = state;
        this.count = count;
        this.totalPrice = totalPrice;
    }
}
