package com.six.yummy.order.responsedto;

import com.six.yummy.cartitem.responsedto.CartItemListResponse;
import com.six.yummy.order.entity.Order;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderResponse {

    private Long id;
    private LocalDateTime orderTime;
    private Long userId;
    private Long restaurantId;
    private List<CartItemListResponse> cartItems;
    private int totalPrice;

    public OrderResponse(Order order, User user, Restaurant restaurant,
        List<CartItemListResponse> cartItems) {
        this.id = order.getId();
        this.orderTime = order.getOrderedAt();
        this.userId = user.getId();
        this.restaurantId = restaurant.getRestaurantId();
        this.totalPrice = order.getTotalPrice();
        this.cartItems = cartItems;
    }

    public OrderResponse(Order order, List<CartItemListResponse> cartItems) {
        this.id = order.getId();
        this.orderTime = order.getOrderedAt();
        this.userId = order.getUser().getId();
        this.restaurantId = order.getRestaurant().getRestaurantId();
        this.totalPrice = order.getTotalPrice();
        this.cartItems = cartItems;
    }
}
