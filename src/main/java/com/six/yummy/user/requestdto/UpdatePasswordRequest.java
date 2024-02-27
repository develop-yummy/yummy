package com.six.yummy.user.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdatePasswordRequest {

    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "영대소문자와 숫자만, 5~15길이만 허용")
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;

}
