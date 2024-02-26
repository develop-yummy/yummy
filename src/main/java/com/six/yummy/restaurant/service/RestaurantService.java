package com.six.yummy.restaurant.service;

import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest, User user) {

        Restaurant restaurant = restaurantRepository.save(Restaurant.createRestaurant(
            restaurantRequest.getRestaurantName(),
            restaurantRequest.getAddress(),
            restaurantRequest.getContent(),
            restaurantRequest.getCategory(),
            user
        ));

        return RestaurantResponse.builder()
            .restaurantId(restaurant.getRestaurantId())
            .restaurantName(restaurant.getRestaurantName())
            .address(restaurant.getAddress())
            .content(restaurant.getContent())
            .category(restaurant.getCategory())
            .build();
    }
}
