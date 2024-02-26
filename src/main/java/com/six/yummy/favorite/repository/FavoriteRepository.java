package com.six.yummy.favorite.repository;

import com.six.yummy.favorite.entity.Favorite;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserAndRestaurant(User findUser, Restaurant restaurant);

    List<Favorite> findAllByUser(User user);
}
