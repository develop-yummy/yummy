package com.six.yummy.restaurant.controller;

import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.restaurant.service.RestaurantService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> saveRestaurant(
        @Valid @RequestBody RestaurantRequest restaurantRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        RestaurantResponse restaurantResponse = restaurantService.saveRestaurant(restaurantRequest, userDetails.getUser());

        return new ResponseEntity<>(restaurantResponse, HttpStatus.CREATED);
    }
}
