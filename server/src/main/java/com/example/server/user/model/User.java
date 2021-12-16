package com.example.server.user.model;

import com.example.server.user.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class User extends TimeStamped {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    private Long height;

    private Long weight;

    private Long walk;

    private String gender;

    private String major;

    public User(SignUpDto signUpDto) {
        this.email = signUpDto.getEmail();
        this.password = signUpDto.getPassword();
        this.name = signUpDto.getName();
        this.height = signUpDto.getHeight();
        this.weight = signUpDto.getWeight();
        this.gender = signUpDto.getGender();
        this.major = signUpDto.getMajor();
    }
}
