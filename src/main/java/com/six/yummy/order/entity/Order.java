package com.six.yummy.order.entity;

import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime orderedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    public Restaurant restaurant;

    @Column
    private int totalPrice;

    public Order(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        this.orderedAt = LocalDateTime.now();
    }

    public void sumTotalPrice(int price) {
        this.totalPrice += price;
    }
}
