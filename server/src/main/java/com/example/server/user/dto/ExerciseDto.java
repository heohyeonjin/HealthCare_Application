package com.example.server.user.dto;

import com.example.server.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ExerciseDto {
    private Long id;
    private String name;
    private Long height;
    private Long weight;
    private Long walk;
    private Long goal;
    private String gender;
    private int totalRanking;
    private int majorRanking;

    public ExerciseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.walk = user.getWalk();
        this.goal = user.getGoal();
        this.gender = user.getGender();
    }
}
