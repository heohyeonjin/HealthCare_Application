package com.example.server.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private Long height;
    private Long weight;
    private String major;
    private String gender;
}
