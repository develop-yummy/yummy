package com.six.yummy.menu.service;

import com.six.yummy.menu.entity.Menu;
import com.six.yummy.menu.repository.MenuRepository;
import com.six.yummy.menu.requestdto.MenuRequest;
import com.six.yummy.menu.responsedto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuResponse saveMenu(MenuRequest menuRequest) {
        Menu menu = menuRepository.save(Menu.createMenu(
            menuRequest.getMenuName(),
            menuRequest.getMenuPrice(),
            menuRequest.getMenuContents(),
            menuRequest.getCategory()));

        return MenuResponse.builder()
            .menuId(menu.getMenuId())
            .menuContents(menu.getMenuContents())
            .menuPrice(menu.getMenuPrice())
            .menuName(menu.getMenuName())
            .category(menu.getCategory())
            .build();
    }
}
