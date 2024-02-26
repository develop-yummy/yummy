package com.six.yummy.order.controller;

import com.six.yummy.order.responsedto.OrderResponse;
import com.six.yummy.order.service.OrderService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/restaurant/{restaurantId}/orders")
    public OrderResponse createOrder(@PathVariable Long restaurantId, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        return orderService.createOrder(restaurantId, userDetails.getUser());
    }

    @GetMapping("/orders")
    public List<OrderResponse> getAllOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getAllOrders(userDetails.getUser());
    }

    @GetMapping("/orders/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrder(orderId, userDetails.getUser());
    }
}
