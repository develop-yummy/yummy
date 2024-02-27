package com.six.yummy.restaurant.service;

import com.six.yummy.global.exception.NotFoundRestaurantException;
import com.six.yummy.global.exception.NotFoundUserException;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantListResponse;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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

    @Transactional(readOnly = true)
    public List<RestaurantListResponse> getRestaurants() {
        return restaurantRepository.findAll().stream().map(
                (Restaurant restaurant) -> new RestaurantListResponse(restaurant.getRestaurantName(),
                    restaurant.getContent()))
            .toList();
    }

    public RestaurantResponse updateRestaurant(RestaurantRequest restaurantRequest, Long userId,
        Long restaurantId) {
        validationUser(userId);

        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurant.update(restaurantRequest.getRestaurantName(), restaurantRequest.getAddress(),
            restaurantRequest.getContent(), restaurantRequest.getCategory());

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

    private void validationUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
            NotFoundUserException::new
        );
    }

    public Restaurant findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
            NotFoundRestaurantException::new
        );
    }

}
