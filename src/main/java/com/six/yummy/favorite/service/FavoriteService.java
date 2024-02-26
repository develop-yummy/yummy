package com.six.yummy.favorite.service;

import com.six.yummy.favorite.entity.Favorite;
import com.six.yummy.favorite.repository.FavoriteRepository;
import com.six.yummy.favorite.responsedto.FavoriteResponse;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.responsedto.RestaurantListResponse;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveFavorite(User user, Long restaurantId) {
        User findUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
            () -> new IllegalArgumentException("유저를 찾지 못했습니다.")
        );

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
            () -> new IllegalArgumentException("식당을 찾지 못했습니다.")
        );

        Optional<Favorite> optionalFavorite = favoriteRepository.findByUserAndRestaurant(findUser,
            restaurant);

        if(optionalFavorite.isPresent()){
            throw new IllegalArgumentException("이미 팔로우가 된 식당입니다.");
        }

        Favorite favorite = new Favorite(restaurant, findUser);

        favoriteRepository.save(favorite);
    }

    @Transactional
    public void deleteFavorite(User user, Long restaurantId, Long favoriteId) {
        userRepository.findByUsername(user.getUsername()).orElseThrow(
            () -> new IllegalArgumentException("유저를 찾지 못했습니다.")
        );

        restaurantRepository.findById(restaurantId).orElseThrow(
            () -> new IllegalArgumentException("식당을 찾지 못했습니다.")
        );

        Favorite favorite = favoriteRepository.findById(favoriteId).orElseThrow(
                () -> new IllegalArgumentException("팔로우한 식당을 찾지 못했습니다.")
            );

        favoriteRepository.delete(favorite);
    }

    public List<RestaurantListResponse> getFavoriteRestaurant(User user) {
        List<Favorite> favorites = favoriteRepository.findAllByUser(user);
        List<RestaurantListResponse> restaurantListResponses = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Restaurant restaurant = restaurantRepository.findById(
                favorite.getRestaurant().getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("식당이 존재하지 않습니다.")
            );

            RestaurantListResponse restaurantListResponse = new RestaurantListResponse(
                restaurant.getRestaurantName(),
                restaurant.getContent()
            );

            restaurantListResponses.add(restaurantListResponse);
        }

        return restaurantListResponses;
    }
}
