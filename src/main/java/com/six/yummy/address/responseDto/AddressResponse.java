package com.six.yummy.address.responseDto;

import lombok.Getter;

@Getter
public class AddressResponse {

    private String username; // 유저 이름

    private String nickname; // 주소 별명

    private String city;

    private String street;

    private String zipcode;

    public AddressResponse(String username, String nickname, String city, String street,
        String zipcode) {
        this.username = username;
        this.nickname = nickname;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
