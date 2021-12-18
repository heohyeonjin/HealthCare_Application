package com.example.server.user.service;

import com.example.server.friend.dto.FriendDto;
import com.example.server.user.dto.IdDoubleCheckDto;
import com.example.server.user.dto.RankingDto;
import com.example.server.user.dto.SignInDto;
import com.example.server.user.dto.SignUpDto;
import com.example.server.user.model.User;
import com.example.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //myId 가져오기
    public Long getmyId(HttpServletRequest req){
        Long myId = (Long) req.getSession().getAttribute("userId");
        return myId;
    }

    // 토큰 저장
    @Transactional
    public void saveToken(User user, String token){
        user.setFcmToken(token);
    }
    // 아이디 중복체크
    public boolean idCheck(IdDoubleCheckDto idDoubleCheckDto) {
        String requestId = idDoubleCheckDto.getEmail();
        User exist = userRepository.findByEmail(requestId);

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
        User findUser = userRepository.findByEmail(userEmail);
        log.info("하하");

        if (findUser != null) {
            if (signInDto.getPassword().equals(findUser.getPassword())) {
                return findUser;
            }
            else {
                log.info("패스워드 오류: " + findUser.getEmail());
                return null;
            }
        }
        return null;
    }

    // 랭킹
    public List<RankingDto> ranking() {
        List<User> findOrder = userRepository.findAll(Sort.by(Sort.Direction.DESC, "walk"));

        return findOrder.stream()
                .map(o-> new RankingDto(o))
                .collect(Collectors.toList());
    }
}
