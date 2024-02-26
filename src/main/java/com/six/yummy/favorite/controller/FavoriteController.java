package com.six.yummy.favorite.controller;

import com.six.yummy.favorite.responsedto.FavoriteResponse;
import com.six.yummy.favorite.service.FavoriteService;
import com.six.yummy.restaurant.responsedto.RestaurantListResponse;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import java.nio.file.Path;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/restaurant")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{restaurant_id}/favorite")
    public ResponseEntity<Void> saveFavorite(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long restaurant_id
    ) {
        favoriteService.saveFavorite(userDetails.getUser(), restaurant_id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/favorite")
    public ResponseEntity<List<RestaurantListResponse>> getFavoriteRestaurant(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        List<RestaurantListResponse> favoriteRestaurants = favoriteService.getFavoriteRestaurant(
            userDetails.getUser());

        return new ResponseEntity<>(favoriteRestaurants, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurant_id}/favorite/{favorite_id}/delete")
    public ResponseEntity<Void> deleteFavorite(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long restaurant_id,
        @PathVariable Long favorite_id
    ) {
        favoriteService.deleteFavorite(userDetails.getUser(), restaurant_id, favorite_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
