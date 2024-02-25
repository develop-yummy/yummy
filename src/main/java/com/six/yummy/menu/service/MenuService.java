package com.six.yummy.menu.service;

import com.six.yummy.menu.entity.Menu;
import com.six.yummy.menu.repository.MenuRepository;
import com.six.yummy.menu.requestdto.MenuRequest;
import com.six.yummy.menu.responsedto.MenuResponse;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public MenuResponse saveMenu(MenuRequest menuRequest, Long restaurantId, User user) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
            () -> new IllegalArgumentException("등록된 가게가 존재하지 않습니다.")
        );

        Menu menu = menuRepository.save(Menu.createMenu(
            menuRequest.getMenuName(),
            menuRequest.getMenuPrice(),
            menuRequest.getMenuContents(),
            menuRequest.getCategory(),
            restaurant));

        return MenuResponse.builder()
            .menuId(menu.getMenuId())
            .menuContents(menu.getMenuContents())
            .menuPrice(menu.getMenuPrice())
            .menuName(menu.getMenuName())
            .category(menu.getCategory())
            .build();
    }
}
