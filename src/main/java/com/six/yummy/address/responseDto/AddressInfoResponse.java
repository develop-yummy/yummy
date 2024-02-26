package com.six.yummy.address.responseDto;

import lombok.Getter;

@Getter
public class AddressInfoResponse {
    private String username;
    private String nickname;
    private String city;
    private String street;
    private String zipcode;

    public AddressInfoResponse(String username, String nickname, String city, String street,
        String zipcode) {
        this.username = username;
        this.nickname = nickname;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
