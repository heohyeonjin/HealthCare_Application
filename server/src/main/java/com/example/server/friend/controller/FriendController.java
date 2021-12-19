package com.example.server.friend.controller;


import com.example.server.fcm.service.FirebaseCloudMessageService;
import com.example.server.friend.dto.FriendCheerMsgDto;
import com.example.server.friend.dto.FriendDetailDto;
import com.example.server.friend.dto.FriendDto;
import com.example.server.friend.dto.AddFriendDto;
import com.example.server.friend.service.FriendService;
import com.example.server.user.model.User;
import com.example.server.user.repository.UserRepository;
import com.example.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FriendController {

    private final UserRepository userRepository;
    private final FriendService friendService;
    private final UserService userService;
    private final FirebaseCloudMessageService fcmService;

    //친구 추가
    @PostMapping("/friend")
    public AddFriendDto addFriend(@RequestBody AddFriendDto addfriendDto, HttpServletRequest req) {
        Long myId = userService.getmyId(req);
        log.info(myId + "");
        AddFriendDto addFriendDto = friendService.addFriend(myId,addfriendDto.getEmail());
        log.info("친구추가DTO:"+addFriendDto.getEmail());
        return addFriendDto;
    }

    //친구 리스트
    @GetMapping("/friends")
    public List<FriendDto> FriendList(HttpServletRequest req) {
        Long myId = userService.getmyId(req);
        Optional<User> findUser = userRepository.findById(myId);
        User user = findUser.get();
        return user.getFriends().stream()
                .map(o -> new FriendDto(o))
                .collect(Collectors.toList());
    }

//    //친구 삭제
//    @GetMapping("/friend/{friendId}")
//    public String FriendDel(@PathVariable Long friendId, HttpServletRequest req) {
//        Long myId = userService.getmyId(req);
//        return friendService.deleteFriend(myId, friendId);
//    }


    //친구 상세 페이지
    @GetMapping("/friend/{friendId}")
    public FriendDetailDto friendDetail(@PathVariable Long friendId) {
        return userService.friendDetailDto(friendId);
    }

    //친구 응원 메시지 보내기
    @PostMapping("/friend/cheer/{friendId}")
    public String Cheering(@PathVariable Long friendId, @RequestBody FriendCheerMsgDto friendCheerMsgDto, HttpServletRequest req) throws IOException {

        // 친구 찾기
        Optional<User> findfriend = userRepository.findById(friendId);
        User friend = findfriend.get();
        String token = friend.getFcmToken();

        //내 정보
        Long myId = userService.getmyId(req);
        Optional<User> findMe = userRepository.findById(myId);
        User me = findMe.get();

        log.info(friendCheerMsgDto.getCheerType());
        String body="";
        if (friendCheerMsgDto.getCheerType().equals("수고")) {
           body = "수고했어요 !!";
        } else if (friendCheerMsgDto.getCheerType().equals("대단")) {
           body = "대단해요!!";
        } else if (friendCheerMsgDto.getCheerType().equals("분발")) {
           body = "분발 하세요!!";
        }

        String title = me.getName();
        log.info("title"+title);
        log.info("body"+body);
        fcmService.sendMessageTo(token, title, body);
        return "true";
    }
}
