package com.six.yummy.menu.entity;

import com.six.yummy.restaurant.entity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private long menuPrice;

    @Column
    private String menuContents;

    @Column
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private Menu(String menuName, long menuPrice, String menuContents, String category,
        Restaurant restaurant) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuContents = menuContents;
        this.category = category;
        this.restaurant = restaurant;
    }

    public static Menu createMenu(String menuName, long menuPrice, String menuContents,
        String category, Restaurant restaurant) {
        return new Menu(menuName, menuPrice, menuContents, category, restaurant);
    }

    public void update(String menuName, long menuPrice, String menuContents, String category) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuContents = menuContents;
        this.category = category;
    }
}
