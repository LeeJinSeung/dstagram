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


}
