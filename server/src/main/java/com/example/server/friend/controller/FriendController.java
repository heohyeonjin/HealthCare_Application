package com.example.server.friend.controller;


import com.example.server.friend.dto.FriendDto;
import com.example.server.friend.service.FriendService;
import com.example.server.user.model.User;
import com.example.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final UserRepository userRepository;
    private final FriendService friendService;

    //친구 추가
    @PostMapping("/friend")
    public String addFriend(@RequestBody String email, HttpServletRequest req){
        Long myId = friendService.getmyId(req);
        return friendService.addFriend(myId, email);
    }

    //친구 리스트
    @GetMapping("/friends")
    public List<FriendDto> FriendList(HttpServletRequest req){
        Long myId = friendService.getmyId(req);
        Optional<User> findUser = userRepository.findById(myId);
        User user = findUser.get();
        return user.getFriends().stream()
                .map(o-> new FriendDto(o))
                .collect(Collectors.toList());
    }

    //친구 삭제
    @GetMapping("/friend/{friendId}")
    public String FriendDel(@PathVariable Long friendId, HttpServletRequest req){
        Long myId = friendService.getmyId(req);
        return friendService.deleteFriend(myId,friendId);
    }
}
