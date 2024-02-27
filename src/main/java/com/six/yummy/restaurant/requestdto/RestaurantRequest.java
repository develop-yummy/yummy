package com.six.yummy.restaurant.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
