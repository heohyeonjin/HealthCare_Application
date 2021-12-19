package com.example.server.friend.dto;


import com.example.server.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddFriendDto {
    private String email;


    //친구 추가 시 리턴 값
    public AddFriendDto(User newfriend){
        this.email = newfriend.getName()+"님 친구 추가";
    }
}
