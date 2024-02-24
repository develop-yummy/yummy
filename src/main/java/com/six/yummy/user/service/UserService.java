package com.six.yummy.user.service;

import com.six.yummy.global.util.PasswordEncoderUtil;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import com.six.yummy.user.jwt.JwtUtil;
import com.six.yummy.user.repository.UserRepository;
import com.six.yummy.user.requestdto.LoginRequest;
import com.six.yummy.user.requestdto.SignupRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoderUtil passwordEncoderUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequest request) {
        String username = request.getUsername();
        String password = passwordEncoderUtil.passwordEncoder().encode(request.getPassword());

        // 회원 중복 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        // email 중복확인
        String email = request.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 Email 입니다.");
        }

        // 휴대폰 번호 중복확인
        String phoneNumber = request.getPhoneNumber();
        Optional<User> checkPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (checkPhoneNumber.isPresent()) {
            throw new IllegalArgumentException(("이미 존재하는 전화번호 입니다."));
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (request.isAdmin()) {
            if (!ADMIN_TOKEN.equals(request.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려서 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, phoneNumber, role);
        userRepository.save(user);
    }

    public void login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // 유저 존재여부 확인
        Optional<User> user = userRepository.findByUsername(username); // todo : User user로 접근하는게 좋은지 - 왜냐면 user.get().getPassword() 이렇게 가져와서..
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }

        // 패스워드 일치 확인
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }

        jwtUtil.createToken(user.get().getUsername(), user.get().getRole());
    }
}
