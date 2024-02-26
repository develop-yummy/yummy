package com.six.yummy.address.entity;

import com.six.yummy.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zipcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Address(String nickname, String city, String street, String zipcode, User user) {
        this.nickname = nickname;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.user = user;
    }

    public void updateAddress(String nickname, String city, String street, String zipcode) {
        this.nickname = nickname;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
