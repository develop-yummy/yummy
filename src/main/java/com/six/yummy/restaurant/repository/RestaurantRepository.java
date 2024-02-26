package com.six.yummy.restaurant.repository;

import com.six.yummy.favorite.entity.Favorite;
import com.six.yummy.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
