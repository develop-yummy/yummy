package com.six.yummy.restaurant.controller;

import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import com.six.yummy.restaurant.responsedto.RestaurantListResponse;
import com.six.yummy.restaurant.responsedto.RestaurantResponse;
import com.six.yummy.restaurant.service.RestaurantService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    ) {
        RestaurantResponse restaurantResponse = restaurantService.saveRestaurant(restaurantRequest,
            userDetails.getUser());

        return new ResponseEntity<>(restaurantResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantListResponse>> getRestaurants() {
        List<RestaurantListResponse> restaurantListResponse = restaurantService.getRestaurants();

        return new ResponseEntity<>(restaurantListResponse, HttpStatus.OK);
    }

    @PatchMapping("/{restaurant_id}/update")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
        @Valid @RequestBody RestaurantRequest restaurantRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long restaurant_id
    ) {
        RestaurantResponse restaurantResponse = restaurantService.updateRestaurant(
            restaurantRequest, userDetails.getUser().getId(), restaurant_id);

        return new ResponseEntity<>(restaurantResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurant_id}/delete")
    public ResponseEntity<Void> deleteRestaurant(
        @PathVariable Long restaurant_id,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        restaurantService.deleteRestaurant(restaurant_id, userDetails.getUser().getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
