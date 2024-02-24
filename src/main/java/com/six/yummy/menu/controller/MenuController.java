package com.six.yummy.menu.controller;

import com.six.yummy.menu.requestdto.MenuRequest;
import com.six.yummy.menu.responsedto.MenuResponse;
import com.six.yummy.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        @Valid @RequestBody MenuRequest menuRequest
    ){
        MenuResponse menuResponse = menuService.saveMenu(menuRequest);

        return new ResponseEntity<>(menuResponse, HttpStatus.CREATED);
    }
}
