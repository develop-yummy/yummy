package com.six.yummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.six.yummy.global.exception.NotFoundRestaurantException;
import com.six.yummy.global.exception.NotFoundUserException;
import com.six.yummy.menu.entity.Menu;
import com.six.yummy.menu.repository.MenuRepository;
import com.six.yummy.menu.requestdto.MenuRequest;
import com.six.yummy.menu.responsedto.MenuResponse;
import com.six.yummy.menu.service.MenuService;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import com.six.yummy.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
public class MenuServiceTest {

    @InjectMocks
    MenuService menuService;

    @Mock
    MenuRepository menuRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("메뉴 등록")
    void test1(){
        //given
        MenuRequest request = new MenuRequest();
        request.setMenuName("testName");
        request.setMenuContents("testContent");
        request.setCategory("testCategory");
        request.setMenuPrice(10000);

        User user = new User(
            "testUsername",
            "testPassword",
            "testEmail",
            "testNumber",
            UserRoleEnum.USER
        );

        Restaurant restaurant = Restaurant.createRestaurant(
            "testName",
            "testAddress",
            "testContent",
            "testCategory",
            user
        );

        Long userId = 1L;
        Long restaurantId = 1L;

        Menu menu = Menu.createMenu(
            "testName",
            10000,
            "testContent",
            "testCategory",
            restaurant
        );

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(restaurantRepository.findById(restaurantId)).willReturn(Optional.of(restaurant));
        given(menuRepository.save(any(Menu.class))).willReturn(menu);

        //when
        MenuResponse menuResponse = menuService.saveMenu(request, 1L, 1L);

        //then
        assertEquals(menuResponse.getMenuName(), request.getMenuName());
    }

    @Test
    @DisplayName("메뉴 등록 유저 조회 실패")
    void test2(){
        //given
        MenuRequest request = new MenuRequest();
        request.setMenuName("testName");
        request.setMenuContents("testContent");
        request.setCategory("testCategory");
        request.setMenuPrice(10000);

        User user = new User(
            "testUsername",
            "testPassword",
            "testEmail",
            "testNumber",
            UserRoleEnum.USER
        );

        Long userId = 1L;

        given(userRepository.findById(userId)).willReturn(Optional.empty());

        //when + then
        assertThrows(NotFoundUserException.class, () -> menuService.saveMenu(request, 1L, 1L));
    }

    @Test
    @DisplayName("메뉴 등록 식당 조회 실패")
    void test3(){
        //given
        MenuRequest request = new MenuRequest();
        request.setMenuName("testName");
        request.setMenuContents("testContent");
        request.setCategory("testCategory");
        request.setMenuPrice(10000);

        User user = new User(
            "testUsername",
            "testPassword",
            "testEmail",
            "testNumber",
            UserRoleEnum.USER
        );

        Restaurant restaurant = Restaurant.createRestaurant(
            "testName",
            "testAddress",
            "testContent",
            "testCategory",
            user
        );

        Long userId = 1L;
        Long restaurantId = 1L;


        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(restaurantRepository.findById(restaurantId)).willReturn(Optional.empty());

        //when
        assertThrows(NotFoundRestaurantException.class, () -> menuService.saveMenu(request, 1L, 1L));
    }
}
