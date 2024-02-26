package com.six.yummy.favorite.responsedto;

import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.user.entity.User;
import lombok.Getter;

@Getter
public class FavoriteResponse {

    private final Long favoriteId;

    private final Restaurant restaurant;

    private final User user;

    public FavoriteResponse(Long favoriteId, Restaurant restaurant, User user) {
        this.favoriteId = favoriteId;
        this.restaurant = restaurant;
        this.user = user;
    }
}
