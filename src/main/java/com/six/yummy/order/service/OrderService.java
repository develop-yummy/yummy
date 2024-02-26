package com.six.yummy.order.service;

import com.six.yummy.cartitem.responsedto.CartItemListResponse;
import com.six.yummy.cartitem.service.CartItemService;
import com.six.yummy.order.entity.Order;
import com.six.yummy.order.repository.OrderRepository;
import com.six.yummy.order.responsedto.OrderResponse;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.restaurant.service.RestaurantService;
import com.six.yummy.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartItemService cartItemService;

    private final RestaurantService restaurantService;

    @Transactional
    public OrderResponse createOrder(Long restaurantId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        Order order = new Order(user, restaurant);
        orderRepository.save(order);
        inputOrderId(order.getId(), user);
        List<CartItemListResponse> cartItems = cartItemService.getOrderItems(order.getId());
        return new OrderResponse(order, user, restaurant, cartItems);
    }

    public List<OrderResponse> getAllOrders(User user) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        for (Order order : orders) {
            orderResponses.add(getOrder(order.getId(), user));
        }
        return orderResponses;
    }

    public OrderResponse getOrder(Long orderId, User user) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new IllegalArgumentException("해당 주문은 삭제되었습니다.")
        );
        validateUser(user.getId(), order);
        List<CartItemListResponse> cartItems = cartItemService.getOrderItems(orderId);
        return new OrderResponse(order, cartItems);
    }

    private void inputOrderId(Long orderId, User user) {
        cartItemService.inputOrderId(orderId, user);
    }

    private void validateUser(Long userId, Order order) {
        if (!userId.equals(order.getUser().getId())) {
            throw new IllegalArgumentException("해당 주문의 유저가 아닙니다.");
        }
    }
}
