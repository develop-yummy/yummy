package com.six.yummy.order.controller;

import com.six.yummy.order.responsedto.OrderResponse;
import com.six.yummy.order.service.OrderService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/restaurants/{restaurantId}/orders")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable Long restaurantId, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        OrderResponse orderResponse = orderService.createOrder(restaurantId, userDetails.getUser());
        return new ResponseEntity<>(orderResponse,HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<OrderResponse> orderResponses = orderService.getAllOrders(userDetails.getUser());
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        OrderResponse orderResponse = orderService.getOrder(orderId, userDetails.getUser());
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
