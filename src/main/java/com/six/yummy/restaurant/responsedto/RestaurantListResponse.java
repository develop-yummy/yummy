package com.six.yummy.restaurant.responsedto;

import lombok.Getter;

@Getter
public class RestaurantListResponse {

    String restaurantName;
    String content;

    public RestaurantListResponse(String restaurantName, String content) {
        this.restaurantName = restaurantName;
        this.content = content;
    }
}
