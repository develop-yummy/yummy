package com.six.yummy.backoffice.responsedto;

import lombok.Getter;

@Getter
public class RestaurantSalesResponse {

    private String restaurantName;
    private String content;
    private String address;
    private String category;
    private int sales;

    public RestaurantSalesResponse(String restaurantName, String content, String address, String category,
        int sales) {
        this.restaurantName = restaurantName;
        this.content = content;
        this.address = address;
        this.category = category;
        this.sales = sales;
    }
}
