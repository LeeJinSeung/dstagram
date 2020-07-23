package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private int pid;
    private String contents;
    private List<String> imageUrl;
    private LocalDateTime created;
    private LocalDateTime updated;
}
