package com.six.yummy.user.controller;

import com.six.yummy.user.lmpl.UserDetailsImpl;
import com.six.yummy.user.requestdto.LoginRequest;
import com.six.yummy.user.requestdto.SignupRequest;
import com.six.yummy.user.responsedto.UserResponse;
import com.six.yummy.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login(@RequestBody LoginRequest request, HttpServletResponse response) {
        userService.login(request, response);
        return "로그인 성공";
    }

    // 회원정보 단건 조회
    @GetMapping
    public UserResponse getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserResponse response = userService.getUser(userDetails.getUser().getId());
        return response;
    }

    // 회원정보 수정(비밀번호 제외)

    // 회원 비밀번호 수정
    // 회원탈퇴

}
