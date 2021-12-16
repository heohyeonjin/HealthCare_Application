package com.example.server.user.controller;

import com.example.server.user.dto.IdDoubleCheckDto;
import com.example.server.user.dto.SignInDto;
import com.example.server.user.dto.SignUpDto;
import com.example.server.user.model.User;
import com.example.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 아이디 중복체크
    @PostMapping("/idCheck")
    public String idDoubleCheck(@RequestBody IdDoubleCheckDto idDoubleCheckDto) {
        boolean exist = userService.idCheck(idDoubleCheckDto);
        if (!exist) return "false";
        return "true";
    }

    // 회원가입
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpDto signUpDto) {
        String savedUser = userService.registerUser(signUpDto);

        if (!Objects.equals(savedUser, signUpDto.getEmail())) {
            return "false";
        }
        return "true";
    }

    // 로그인
    @PostMapping("/login")
    public String loginRequest(@RequestBody SignInDto signInDto, HttpServletRequest servletRequest) {
        User user = userService.login(signInDto);
        if (user == null) {
            return "false";
        }
        servletRequest.getSession().setAttribute("userId", user.getId());
        log.info("로그인 성공: " + user.getName());
        return "true";
    }

}
