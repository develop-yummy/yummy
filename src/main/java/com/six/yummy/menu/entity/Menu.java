package com.six.yummy.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private long menuPrice;

    @Column
    private String menuContents;

    @Column
    private String category;

    private Menu(String menuName, long menuPrice, String menuContents, String category) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuContents = menuContents;
        this.category = category;
    }

    public static Menu createMenu(String menuName, long menuPrice, String menuContents, String category){
        return new Menu(menuName, menuPrice, menuContents, category);
    }
}
