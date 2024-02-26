package com.six.yummy.menu.responsedto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuResponse {

    private final Long menuId;

    private final String menuName;

    private final long menuPrice;

    private final String menuContents;

    private final String category;

    @Builder
    public MenuResponse(Long menuId, String menuName, long menuPrice, String menuContents,
        String category) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuContents = menuContents;
        this.category = category;
    }
}
