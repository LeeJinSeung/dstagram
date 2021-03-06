package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int pid;

    @Column
    private int uid;

    @Column(nullable = false)
    private String contents;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    public Post() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Post(int pid, int uid, String contents, LocalDateTime created, LocalDateTime updated) {
        this.pid = pid;
        this.uid = uid;
        this.contents = contents;
        this.created = created;
        this.updated = updated;
    }
}
