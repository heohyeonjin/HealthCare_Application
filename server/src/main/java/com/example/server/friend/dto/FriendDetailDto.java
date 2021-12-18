package com.example.server.friend.dto;

import com.example.server.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class FriendDetailDto {

    private String name;
    private String email;
    private String gender;
    private String major;
    private Long walk;

    public FriendDetailDto(User friend){
        this.name = friend.getName();
        this.email = friend.getEmail();
        this.gender = friend.getGender();
        this.major = friend.getMajor();
        this.walk = friend.getWalk();
    }
}
