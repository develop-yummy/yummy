package com.six.yummy.restaurant.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RestaurantRequest {

    @NotBlank
    String restaurantName;
    @NotBlank
    String address;
    @NotBlank
    String content;
    @NotBlank
    String category;
}
