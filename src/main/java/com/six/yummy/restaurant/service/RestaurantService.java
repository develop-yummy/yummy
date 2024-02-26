package com.six.yummy.restaurant.service;

import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

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

    public RestaurantResponse updateRestaurant(RestaurantRequest restaurantRequest, Long userId, Long restaurantId) {
        validationUser(userId);

        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurant.update(restaurantRequest.getRestaurantName(), restaurantRequest.getAddress(), restaurantRequest.getContent(), restaurantRequest.getCategory());

        return RestaurantResponse.builder()
            .restaurantId(restaurant.getRestaurantId())
            .restaurantName(restaurant.getRestaurantName())
            .address(restaurant.getAddress())
            .content(restaurant.getContent())
            .category(restaurant.getCategory())
            .build();
    }

    public void deleteRestaurant(Long restaurantId, Long userId) {
        validationUser(userId);

        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    private void validationUser(Long userId){
        userRepository.findById(userId).orElseThrow(
            () -> new IllegalArgumentException("등록된 유저가 존재하지 않습니다.")
        );
    }

    private Restaurant findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
            () -> new IllegalArgumentException("등록된 식당이 존재하지 않습니다.")
            );
    }
}
