package com.six.yummy.review.entity;

import com.six.yummy.like.entity.Like;
import com.six.yummy.order.entity.Order;
import com.six.yummy.review.requestdto.ReviewRequest;
import com.six.yummy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Short point;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long restaurantId;

    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id" , nullable = false)
    private Order order;

    @OneToMany (mappedBy = "review")
    private List<Like> likeList = new ArrayList<>();

    public Review (Order order, User user, ReviewRequest reviewRequest){
        this.username = getUsername();
        this.point = reviewRequest.getPoint();
        this.content = reviewRequest.getContent();
        this.restaurantId = order.getRestaurantId();
        this.user = user;
        this.order = order;
        this.createdAt = LocalDateTime.now();

    }

    public int getLikesCount() {
        return likeList.size();
    }

}