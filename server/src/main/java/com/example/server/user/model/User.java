package com.example.server.user.model;

import com.example.server.friend.model.Friend;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    private Long height;

    private Long weight;

    private String gender;

    private String major;


    //친구 추가
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

}
