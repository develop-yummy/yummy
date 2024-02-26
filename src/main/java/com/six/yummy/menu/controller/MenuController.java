package com.six.yummy.menu.controller;

import com.six.yummy.menu.requestdto.MenuRequest;
import com.six.yummy.menu.responsedto.MenuResponse;
import com.six.yummy.menu.service.MenuService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/restaurant")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<MenuResponse> saveMenu(
        @PathVariable Long restaurantId,
        @Valid @RequestBody MenuRequest menuRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        MenuResponse menuResponse = menuService.saveMenu(menuRequest, restaurantId, userDetails.getUser());

        return new ResponseEntity<>(menuResponse, HttpStatus.CREATED);
    }
}
