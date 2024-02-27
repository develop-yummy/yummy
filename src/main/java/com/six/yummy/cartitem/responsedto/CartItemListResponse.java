package com.six.yummy.cartitem.responsedto;

import lombok.Getter;

@Getter
public class CartItemListResponse {

    private Long menuId;
    private int count;
    private int totalPrice;


    public CartItemListResponse(Long menuId, int count, int totalPrice) {
        this.menuId = menuId;
        this.count = count;
        this.totalPrice = totalPrice;
    }
}
