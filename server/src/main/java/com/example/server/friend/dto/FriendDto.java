package com.example.server.friend.dto;

import com.example.server.friend.model.Friend;
import com.example.server.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDto {

    private Long userId;
    private String name;
    private String email;
    private Long walk;

    public FriendDto(Friend friend) {
        User user = friend.getFriend();
        this.userId = user.getId();
//        this.name = user.getName();
        this.email = user.getEmail();
    }
}
