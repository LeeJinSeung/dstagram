package com.landvibe.dstagram.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Profile {
    private String email;
    private String name;
    private String nickname;
    private List<Integer> pid;
    private List<String> imageUrl;
    private int count;

    public Profile(User user, List<Integer> pid, List<String> imageUrl) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.pid = pid;
        this.imageUrl = imageUrl;
        this.count = pid.size();
    }
}
