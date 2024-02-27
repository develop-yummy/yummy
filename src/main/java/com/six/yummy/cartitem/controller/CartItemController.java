package com.six.yummy.cartitem.controller;


import com.six.yummy.cartitem.responsedto.CartItemListResponse;
import com.six.yummy.cartitem.responsedto.CartItemResponse;
import com.six.yummy.cartitem.service.CartItemService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/menus/{menuId}/cartitems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/{count}")
    public ResponseEntity<CartItemResponse> cartItemAdd(
        @PathVariable Long menuId,
        @PathVariable int count,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return cartItemService.cartItemAdd(menuId, count, userDetails.getUser());
    }

    @GetMapping("")
    public List<CartItemListResponse> cartItemRead(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return cartItemService.cartItemRead(userDetails.getUser());
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<CartItemResponse> cartItemDelete(
        @PathVariable Long cartItemId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // Spring Security 추가 후 @AuthenticationPrincipal 추가
        return cartItemService.cartItemDelete(cartItemId, userDetails.getUser());
    }

    @DeleteMapping
    public ResponseEntity<CartItemResponse> cartItemDeleteAll(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // Spring Security 추가 후 @AuthenticationPrincipal 추가
        return cartItemService.cartItemDeleteAll(userDetails.getUser());
    }

}
