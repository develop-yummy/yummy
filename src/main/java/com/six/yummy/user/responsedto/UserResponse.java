package com.six.yummy.user.responsedto;

import com.six.yummy.address.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String email;
    private String phoneNumber;

}
