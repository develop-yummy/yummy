package com.six.yummy.user.controller;

import com.six.yummy.user.requestdto.LoginRequest;
import com.six.yummy.user.requestdto.SignupRequest;
import com.six.yummy.user.responsedto.LoginResponse;
import com.six.yummy.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequest requestDto,
        BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            return "회원가입도중 에러가 발생했습니다.";
        }
        userService.signup(requestDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return response;
    }

}
