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

    public MenuResponse saveMenu(MenuRequest menuRequest, Long restaurantId, Long userId) {

        validationUser(userId);

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

    public MenuResponse updateMenu(Long menuId, MenuRequest menuRequest, Long restaurantId, Long userId) {

        validationUser(userId);
        validationRestaurant(restaurantId);

        Menu menu = findMenuById(menuId);

        menu.update(menuRequest.getMenuName(), menuRequest.getMenuPrice(), menuRequest.getMenuContents(), menuRequest.getCategory());

        return MenuResponse.builder()
            .menuId(menu.getMenuId())
            .menuContents(menu.getMenuContents())
            .menuPrice(menu.getMenuPrice())
            .menuName(menu.getMenuName())
            .category(menu.getCategory())
            .build();
    }

    public void deleteMenu(Long menuId, Long restaurantId, Long userId) {

        validationUser(userId);
        validationUser(restaurantId);

        Menu menu = findMenuById(menuId);

        menuRepository.delete(menu);
    }

    private void validationUser(Long userId){
        userRepository.findById(userId).orElseThrow(
            () -> new IllegalArgumentException("등록된 유저가 존재하지 않습니다.")
        );
    }

    private void validationRestaurant(Long restaurantId){
        restaurantRepository.findById(restaurantId).orElseThrow(
            () -> new IllegalArgumentException("등록된 가게가 존재하지 않습니다.")
        );
    }

    private Menu findMenuById(Long menuId){
        return menuRepository.findById(menuId).orElseThrow(
            () -> new IllegalArgumentException("메뉴가 존재하지 않습니다.")
        );
    }
}
