package com.six.yummy.menu.responsedto;

import lombok.Getter;

@Getter
public class MenuListResponse {
    private String menuName;
    private long menuPrice;

    public MenuListResponse(String menuName, long menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}
