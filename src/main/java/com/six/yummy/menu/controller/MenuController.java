package com.six.yummy.menu.controller;

import com.six.yummy.menu.requestdto.MenuRequest;
import com.six.yummy.menu.responsedto.MenuListResponse;
import com.six.yummy.menu.responsedto.MenuResponse;
import com.six.yummy.menu.service.MenuService;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
@RequestMapping("/v1/restaurant")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{restaurant_id}")
    public ResponseEntity<MenuResponse> saveMenu(
        @PathVariable Long restaurant_id,
        @Valid @RequestBody MenuRequest menuRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        MenuResponse menuResponse = menuService.saveMenu(menuRequest, restaurant_id, userDetails.getUser().getId());

        return new ResponseEntity<>(menuResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{restaurant_id}/{menu_id}")
    public ResponseEntity<MenuResponse> getMenu(
        @PathVariable Long restaurant_id,
        @PathVariable Long menu_id,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        MenuResponse menuResponse = menuService.getMenu(restaurant_id, menu_id,
            userDetails.getUser().getId());

        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    @GetMapping("/{restaurant_id}")
    public ResponseEntity<List<MenuListResponse>> getMenus(
        @PathVariable Long restaurant_id
    ){
        List<MenuListResponse> menusResponse = menuService.getMenus(restaurant_id);

        return new ResponseEntity<>(menusResponse, HttpStatus.OK);
    }

    @PatchMapping("/{restaurant_id}/{menu_id}/update")
    public ResponseEntity<MenuResponse> updateMenu(
        @PathVariable Long menu_id,
        @PathVariable Long restaurant_id,
        @Valid @RequestBody MenuRequest menuRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        MenuResponse menuResponse = menuService.updateMenu(menu_id, menuRequest, restaurant_id,
            userDetails.getUser().getId());

        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurant_id}/{menu_id}/delete")
    public ResponseEntity<Void> deleteMenu(
        @PathVariable Long menu_id,
        @PathVariable Long restaurant_id,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        menuService.deleteMenu(menu_id, restaurant_id, userDetails.getUser().getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
