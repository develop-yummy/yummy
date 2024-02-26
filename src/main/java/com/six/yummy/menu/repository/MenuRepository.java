package com.six.yummy.menu.repository;

import com.six.yummy.menu.entity.Menu;
import com.six.yummy.restaurant.entity.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByRestaurant(Restaurant restaurant);
}
