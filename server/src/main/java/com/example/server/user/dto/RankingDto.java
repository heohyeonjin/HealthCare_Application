package com.example.server.user.dto;

import com.example.server.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingDto {
    private Long userId;
    private String userName;
    private String userMajor;
    private Long userWalk;

    public RankingDto(User user) {
        userId = user.getId();
        userName = user.getName();
        userMajor = user.getMajor();
        userWalk = user.getWalk();
    }
}
