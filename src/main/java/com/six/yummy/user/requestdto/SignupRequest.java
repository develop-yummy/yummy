package com.six.yummy.user.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    private boolean admin = false;

    private String adminToken = "";



}
