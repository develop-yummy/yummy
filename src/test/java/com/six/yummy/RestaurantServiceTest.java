package com.six.yummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.repository.RestaurantRepository;
import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.restaurant.service.RestaurantService;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import com.six.yummy.user.jwt.JwtUtil;
import com.six.yummy.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
}
