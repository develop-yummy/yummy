package com.six.yummy.restaurant.entity;

import com.six.yummy.restaurant.requestdto.RestaurantRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long restaurantId;

    @Column(nullable = false)
    String restaurantName;

    @Column(nullable = false)
    String address;

    String content;

    String category;

    private Restaurant(String restaurantName, String address, String content, String category) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.content = content;
        this.category = category;
    }

    //생성 메서드
    public static Restaurant createRestaurant(String restaurantName, String address, String content, String category){

        return new Restaurant(restaurantName, address, content, category);
    }
}
