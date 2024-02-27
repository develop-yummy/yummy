package com.six.yummy.favorite.service;

import com.six.yummy.favorite.entity.Favorite;
import com.six.yummy.favorite.repository.FavoriteRepository;
import com.six.yummy.global.exception.DataIntegrityViolationFollowException;
import com.six.yummy.global.exception.NotFoundFollowException;
import com.six.yummy.global.exception.NotFoundRestaurantException;
import com.six.yummy.global.exception.NotFoundUserException;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.responsedto.RestaurantListResponse;
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
            NotFoundUserException::new
        );

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
            NotFoundRestaurantException::new
        );

        Optional<Favorite> optionalFavorite = favoriteRepository.findByUserAndRestaurant(findUser,
            restaurant);

        if (optionalFavorite.isPresent()) {
            throw new DataIntegrityViolationFollowException();
        }

        Favorite favorite = new Favorite(restaurant, findUser);

        favoriteRepository.save(favorite);
    }

    @Transactional
    public void deleteFavorite(User user, Long restaurantId, Long favoriteId) {
        userRepository.findByUsername(user.getUsername()).orElseThrow(
            NotFoundUserException::new
        );

        restaurantRepository.findById(restaurantId).orElseThrow(
            NotFoundRestaurantException::new
        );

        Favorite favorite = favoriteRepository.findById(favoriteId).orElseThrow(
            NotFoundFollowException::new
        );

        favoriteRepository.delete(favorite);
    }

    public List<RestaurantListResponse> getFavoriteRestaurant(User user) {
        List<Favorite> favorites = favoriteRepository.findAllByUser(user);
        List<RestaurantListResponse> restaurantListResponses = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Restaurant restaurant = restaurantRepository.findById(
                favorite.getRestaurant().getRestaurantId()).orElseThrow(
                NotFoundRestaurantException::new
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
