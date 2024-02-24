package com.six.yummy.restaurant.responsedto;

import com.six.yummy.restaurant.entity.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RestaurantResponse {
    Long restaurantId;
    String restaurantName;
    String address;
    String content;
    String category;

    @Builder
    public RestaurantResponse(Long restaurantId, String restaurantName, String address, String content, String category) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.content = content;
        this.category = category;
    }
}
