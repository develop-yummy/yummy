package com.six.yummy.menu.responsedto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuResponse {

    private Long menuId;

    private String menuName;

    private long menuPrice;

    private String menuContents;

    private String category;

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
