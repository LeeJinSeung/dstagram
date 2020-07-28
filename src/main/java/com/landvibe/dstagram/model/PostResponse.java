package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponse {
    private int pid;
    private int uid;
    private String contents;
    private List<String> imageUrl;
    private LocalDateTime created;
    private LocalDateTime updated;

    public PostResponse() {

    }

    public PostResponse(Post post, List<String> imageUrl) {
        this.pid = post.getPid();
        this.uid = post.getUid();
        this.contents = post.getContents();
        this.created = post.getCreated();
        this.updated = post.getUpdated();
        this.imageUrl = imageUrl;
    }
}
