package com.example.server.user.service;

import com.example.server.user.dto.IdDoubleCheckDto;
import com.example.server.user.dto.SignInDto;
import com.example.server.user.dto.SignUpDto;
import com.example.server.user.model.User;
import com.example.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 아이디 중복체크
    public boolean idCheck(IdDoubleCheckDto idDoubleCheckDto) {
        String requestId = idDoubleCheckDto.getEmail();
        User exist = userRepository.findByUserEmail(requestId);

        // 존재하면 false
        return exist == null;
    }

    // 회원가입
    @Transactional
    public String registerUser(SignUpDto signUpDto) {

        User newUser = new User(signUpDto);
        userRepository.save(newUser);

        return newUser.getEmail();
    }

    // 로그인
    public User login(SignInDto signInDto) {

        String userEmail = signInDto.getEmail();
        User findUser = userRepository.findByUserEmail(userEmail);

        if (findUser != null) {
            if (signInDto.getPassword() == findUser.getPassword()) {
                return findUser;
            }
            else {
                log.info("패스워드 오류: " + findUser.getEmail());
                return null;
            }
        }
        return null;
    }
}
