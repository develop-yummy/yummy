package com.six.yummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.six.yummy.global.exception.NotFoundRestaurantException;
import com.six.yummy.global.exception.NotFoundUserException;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantListResponse;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.restaurant.service.RestaurantService;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import com.six.yummy.user.jwt.JwtUtil;
import com.six.yummy.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
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
public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    JwtUtil jwtUtil;

    @Test
    @DisplayName("식당 생성")
    void test1(){
        //given
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("testName");
        request.setAddress("testAddress");
        request.setContent("testContent");
        request.setCategory("testCategory");

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

        given(restaurantRepository.save(any(Restaurant.class))).willReturn(restaurant);

        //when
        RestaurantResponse restaurantResponse = restaurantService.saveRestaurant(request, user);

        //then
        assertEquals(request.getRestaurantName(), restaurantResponse.getRestaurantName());
        assertEquals(restaurant.getRestaurantName(), restaurantResponse.getRestaurantName());
    }

    @Test
    @DisplayName("식당 목록 조회")
    void test2(){
        //given
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

        Restaurant restaurant2 = Restaurant.createRestaurant(
            "testName2",
            "testAddress2",
            "testContent2",
            "testCategory2",
            user
        );

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant);
        restaurants.add(restaurant2);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        //when
        List<RestaurantListResponse> responses = restaurantService.getRestaurants();

        //then
        assertEquals(responses.get(0).getRestaurantName(), restaurant.getRestaurantName());
        assertEquals(responses.get(1).getRestaurantName(), restaurant2.getRestaurantName());
    }

    @Test
    @DisplayName("식당 정보 수정")
    void test3(){
        //given
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("testName");
        request.setAddress("testAddress");
        request.setContent("testContent");
        request.setCategory("testCategory");

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
        given(restaurantRepository.findById(restaurantId)).willReturn(Optional.of(restaurant));

        //when
        RestaurantResponse restaurantResponse = restaurantService.updateRestaurant(request, userId,
            restaurantId);

        //then
        assertEquals(restaurantResponse.getRestaurantName(), request.getRestaurantName());
    }

    @Test
    @DisplayName("식당 정보 수정 NotFoundUserException")
    void test4(){
        //given
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("testName");
        request.setAddress("testAddress");
        request.setContent("testContent");
        request.setCategory("testCategory");

        Long userId = 1L;
        Long restaurantId = 1L;

        given(userRepository.findById(userId)).willReturn(Optional.empty());

        //when + then
        assertThrows(NotFoundUserException.class, () -> restaurantService.updateRestaurant(request, userId, restaurantId));
    }

    @Test
    @DisplayName("식당 정보 수정 NotFoundRestaurantException")
    void test5() {
        //given
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("testName");
        request.setAddress("testAddress");
        request.setContent("testContent");
        request.setCategory("testCategory");

        User user = new User(
            "testUsername",
            "testPassword",
            "testEmail",
            "testNumber",
            UserRoleEnum.USER
        );

        Long userId = 1L;
        Long restaurantId = 1L;

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(restaurantRepository.findById(restaurantId)).willReturn(Optional.empty());

        //when + then
        assertThrows(NotFoundRestaurantException.class,
            () -> restaurantService.updateRestaurant(request, userId, restaurantId));
    }

    @Test
    @DisplayName("식당 등록 삭제")
    void test6(){
        //given
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("testName");
        request.setAddress("testAddress");
        request.setContent("testContent");
        request.setCategory("testCategory");

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
        given(restaurantRepository.findById(restaurantId)).willReturn(Optional.of(restaurant));

        //when
        restaurantService.deleteRestaurant(restaurantId, userId);

        //then
        verify(restaurantRepository, times(1)).delete(restaurant);
    }
}
