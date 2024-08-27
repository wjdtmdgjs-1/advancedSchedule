package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.config.PasswordEncoder;
import com.sparta.upgradeschedule.dto.user.requestDto.UserLoginRequestDto;
import com.sparta.upgradeschedule.dto.user.requestDto.UserSaveRequestDto;
import com.sparta.upgradeschedule.dto.user.requestDto.UserUpdateRequestDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserGetResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserLoginResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserSaveResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserUpdateResponseDto;
import com.sparta.upgradeschedule.entity.User;
import com.sparta.upgradeschedule.entity.UserRoleEnum;
import com.sparta.upgradeschedule.exception.UnauthorizedException;
import com.sparta.upgradeschedule.jwt.JwtUtil;
import com.sparta.upgradeschedule.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    public UserSaveResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        String username = userSaveRequestDto.getUserName();
        String password = passwordEncoder.encode(userSaveRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUserName(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // email 중복확인
        String email = userSaveRequestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;
        if (userSaveRequestDto.getAdminToken() != null) {
            if (!ADMIN_TOKEN.equals(userSaveRequestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, email, password, role);
        userRepository.save(user);
        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        return new UserSaveResponseDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                token,
                user.getUserCreateDate(),
                user.getUserUpdateDate());
    }

    public UserGetResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("유저가 없습니다."));
        return new UserGetResponseDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getUserCreateDate(),
                user.getUserUpdateDate());
    }

    public List<UserGetResponseDto> getUsers() {
        List<User> userlist = userRepository.findAll();
        List<UserGetResponseDto> dto = new ArrayList<>();
        for (User u : userlist) {
            dto.add(new UserGetResponseDto(u.getId(),
                    u.getUserName(),
                    u.getEmail(),
                    u.getUserCreateDate(),
                    u.getUserUpdateDate()));
        }
        return dto;
    }

    //유저이름, 유저이메일 수정가능합니다
    public UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("유저가 없습니다."));
        user.update(userUpdateRequestDto.getUserName(), userUpdateRequestDto.getEmail());
        return new UserUpdateResponseDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getUserCreateDate(),
                user.getUserUpdateDate());
    }


    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("유저가 없습니다."));
        userRepository.delete(user);
    }

    public UserLoginResponseDto login(UserLoginRequestDto userloginRequestDto, HttpServletResponse res) {
        String email = userloginRequestDto.getEmail();
        log.info(email);
        String password = userloginRequestDto.getPassword();

        //사용자확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        jwtUtil.addJwtToCookie(token, res);

        return new UserLoginResponseDto(token);
    }
}
