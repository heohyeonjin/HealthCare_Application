package com.example.server.fcm.controller;


import com.example.server.fcm.dto.TokenDto;
import com.example.server.friend.service.FriendService;
import com.example.server.user.model.User;
import com.example.server.user.repository.UserRepository;
import com.example.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FcmController {
    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/getToken")
    public String tokenUpdate(@RequestBody TokenDto token, HttpServletRequest request){
        Long userId = userService.getmyId(request);
        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();
        log.info("토큰 :" + token.getToken());
        userService.saveToken(user,token.getToken());
        log.info("토큰 저장");
        return "true";
    }
}
