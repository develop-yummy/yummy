package com.six.yummy;

import com.six.yummy.global.exception.DataIntegrityViolationLikeException;
import com.six.yummy.order.entity.Order;
import com.six.yummy.order.repository.OrderRepository;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.review.entity.Review;
import com.six.yummy.review.repository.ReviewRepository;
import com.six.yummy.review.requestdto.ReviewRequest;
import com.six.yummy.review.responsedto.ReviewResponse;
import com.six.yummy.review.service.ReviewService;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ReviewRepository reviewRepository;


    @Test
    @DisplayName("유저는 존재하고 리뷰는 등록 전인 경우")
    void test1() {

        ReviewRequest reviewRequest = new ReviewRequest();

        User user = new User(
            "testUsername",
            "testPassword",
            "testEmail",
            "testNumber",
            UserRoleEnum.USER
        );
        Long userId = 1L;

        Restaurant restaurant = Restaurant.createRestaurant(
            "testName",
            "testAddress",
            "testContent",
            "testCategory",
            user
        );
        Long restaurantId = 1L;

        Order order = new Order(user, restaurant);
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(reviewRepository.findByUserAndOrder(user, order)).thenReturn(Optional.empty());

        // when
        ReviewResponse response = reviewService.createReview(orderId, user, reviewRequest);

        // then
        assertNotNull(response);
        verify(reviewRepository, times(1)).save(any(Review.class)); //리뷰가 저장되었음을 확인

    }

    @Test
    @DisplayName("리뷰가 이미 존재할 때 다시 리뷰를 등록하지 않는지 확인")
    void test2() {

        //given
        ReviewRequest reviewRequest = new ReviewRequest();

        User user = new User(
            "testUsername",
            "testPassword",
            "testEmail",
            "testNumber",
            UserRoleEnum.USER
        );
        Long userId = 1L;

        Restaurant restaurant = Restaurant.createRestaurant(
            "testName",
            "testAddress",
            "testContent",
            "testCategory",
            user
        );
        Long restaurantId = 1L;

        Order order = new Order(user, restaurant);
        Long orderId = 1L;

        //when

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(reviewRepository.findByUserAndOrder(user, order)).thenReturn(Optional.of(new Review()));


        // then
        assertThrows(DataIntegrityViolationLikeException.class, () -> {
            reviewService.createReview(orderId, user, reviewRequest);
        }); //리뷰가 이미 존재하면 예외처리를 반환하는지 확인

        verify(reviewRepository, never()).save(any(Review.class)); // 리뷰가 저장되지 않았음을 확인
    }


}
