package com.six.yummy.user.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequest { // JwtAuthenticationFilter 사용

    @NotBlank
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

}
