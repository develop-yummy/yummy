package com.six.yummy.user.responsedto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String token;


}
