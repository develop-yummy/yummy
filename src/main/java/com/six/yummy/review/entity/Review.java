package com.six.yummy.review.entity;

import com.six.yummy.like.entity.Like;
import com.six.yummy.order.entity.Order;
import com.six.yummy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Short point;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oder_id" , nullable = false)
    private Order order;
    @OneToMany (mappedBy = "review")
    private List<Like> likeList = new ArrayList<>();

}