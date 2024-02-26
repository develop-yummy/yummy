package com.six.yummy.address.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddressUpdateRequest {

    private String nickname;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String password;

}
