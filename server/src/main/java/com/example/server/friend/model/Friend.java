package com.example.server.friend.model;


import com.example.server.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="friend_id")
    private Long id;

    @OneToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name="newfriend_id")
    private User friend;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="my_id")
    private User user;

    public Friend(User user, User friend){
        this.user = user;
        this.friend = friend;
    }

    //친구 추가
    public static Friend createFriendShip(User me, User friend){
        Friend newfriend = new Friend(me,friend);
        me.getFriends().add(newfriend);
        return newfriend;
    }





}
