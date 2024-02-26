package com.six.yummy.user.service;

import com.six.yummy.global.util.PasswordEncoderUtil;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import com.six.yummy.user.jwt.JwtUtil;
import com.six.yummy.user.repository.UserRepository;
import com.six.yummy.user.requestdto.LoginRequest;
import com.six.yummy.user.requestdto.SignupRequest;
import com.six.yummy.user.requestdto.UpdateInfoRequest;
import com.six.yummy.user.requestdto.UpdatePasswordRequest;
import com.six.yummy.user.responsedto.UserResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // 회원 중복확인
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

    public void login(LoginRequest request, HttpServletResponse response) {
        String email = request.getEmail();
        String password = request.getPassword();

        // 이메일 존재 확인
        Optional<User> user = userRepository.findByEmail(
            email); // todo : User user로 접근하는게 좋은지 - 왜냐면 user.get().getPassword() 이렇게 가져와서..
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 유저 이메일입니다.");
        }

        // 패스워드 일치 확인
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }

        response.setHeader(jwtUtil.AUTHORIZATION_HEADER,
            jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));
    }


    // 회원정보 단건 조회
    public UserResponse getUser(Long id) {
        User user = findUser(id);
        return new UserResponse(user.getUsername(), user.getEmail(), user.getPhoneNumber());

    }

    // 회원정보 수정(비밀번호 제외)
    @Transactional
    public UserResponse updateUser(UpdateInfoRequest request, Long id) {
        User user = findUser(id);
        user.updateUser(request.getUsername(), request.getEmail(), request.getPhoneNumber());
        return new UserResponse(user.getUsername(), user.getEmail(), user.getPhoneNumber());

    }

    // 회원 비밀번호 수정
    @Transactional
    public void updatePassword(UpdatePasswordRequest request, Long id) {
        User user = findUser(id);
        if (!passwordEncoderUtil.passwordEncoder().matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("저장된 비밀번호와 일치하지 않습니다");
        }
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new RuntimeException("새로운 패스워드를 다시 확인해주세요.");
        }
        user.updatePassword(request.getNewPassword());
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
    }
}
