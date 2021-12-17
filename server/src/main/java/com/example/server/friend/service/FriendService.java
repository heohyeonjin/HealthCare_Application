package com.example.server.friend.service;


import com.example.server.friend.dto.FriendDto;
import com.example.server.friend.model.Friend;
import com.example.server.friend.repository.FriendRepository;
import com.example.server.user.model.User;
import com.example.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    //myId 가져오기
    public Long getmyId(HttpServletRequest req){
        Long myId = (Long) req.getSession().getAttribute("userId");
        return myId;
    }
    //친구 추가
    @Transactional
    public String addFriend(Long myId, String friendEmail){
        Optional<User> findme = userRepository.findById(myId);
        User me = findme.get();

        //본인 등록 방지
        log.info("친구 이메일:"+friendEmail);
        User newfriend = userRepository.findByEmail(friendEmail);
        log.info(newfriend.getEmail());
        if(me==newfriend) {
            log.info("본인 등록 오류");
            return"본인 등록 오류";
        }

        //중복 등록 방지
        boolean check = me.getFriends().stream()
                .anyMatch(a->a.getFriend().getId() == newfriend.getId());

        if(check){
            log.info("중복 등록 오류");
            return "중복 등록 오류";
        }

        // 친구 등록
        if(newfriend != null){
            log.info("new friend 존재");
            Friend friend = Friend.createFriendShip(me,newfriend);
            friendRepository.save(friend);
            return newfriend.getEmail()+"등록";
        }
        return "왜안됨";
    }


    //친구 삭제
    @Transactional
    public String deleteFriend(long myId, long friendId){
        if(myId == friendId) return "본인을 삭제할 수 없습니다";

        User me = userRepository.findById(myId).orElseThrow(
                () -> new NullPointerException("접근 오류")
        );
        Optional<Friend> first = me.getFriends().stream()
                .filter(a -> a.getFriend().getId()==friendId)
                .findFirst();

        if(first.isPresent()){
            Friend friend = first.get();
            friendRepository.deleteById(friend.getId());
            return "친구 삭제 완료";
        }
        return "친구 삭제 오류";
    }


    @Transactional
    public List<FriendDto> getAllFriend(Long userId){
        Optional<User> finduser = userRepository.findById(userId);
        User user = finduser.get();
        List<Friend> friendList = user.getFriends();

        return friendList.stream()
                .map(o->new FriendDto(o))
                .collect(Collectors.toList());
    }

}
