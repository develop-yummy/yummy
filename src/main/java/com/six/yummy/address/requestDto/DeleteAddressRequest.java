package com.six.yummy.address.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteAddressRequest {

    @NotBlank
    private String password;

}
