package com.six.yummy.cartitem.entity;

import com.six.yummy.menu.entity.Menu;
import com.six.yummy.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int count;

    @Column
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    // 연관관계 이후 user와 menu 연결
    @Builder
    public CartItem(int totalPrice, int count, User user, Menu menu) {
        // 외래키 생성 후 user, menu부분 채우기
        this.totalPrice = totalPrice;
        this.count = count;
        this.user = user;
        this.menu = menu;
    }

    public void updateCartItemQuantityAndTotalPrice(int additionalCount) {
        this.count = count + additionalCount;
        this.totalPrice = (int) (this.getMenu().getMenuPrice() * this.getCount());
    }

    public void inputOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
