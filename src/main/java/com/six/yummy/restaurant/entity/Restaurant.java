package com.six.yummy.restaurant.entity;

import com.six.yummy.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String address;

    @Column
    private String content;

    @Column
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Restaurant(String restaurantName, String address, String content, String category, User user) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.content = content;
        this.category = category;
        this.user = user;
    }

    //생성 메서드
    public static Restaurant createRestaurant(String restaurantName, String address, String content, String category, User user){

        return new Restaurant(restaurantName, address, content, category, user);
    }

    public void update(String restaurantName, String address, String content, String category){
        this.restaurantName = restaurantName;
        this.address = address;
        this.content = content;
        this.category = category;
    }
}
