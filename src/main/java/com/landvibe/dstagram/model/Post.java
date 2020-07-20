package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Column(updatable = false, nullable = false)
    private int id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    public Post() {
    }

    public Post(int id, String title, String contents, String imageUrl, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.created = created;
        this.updated = updated;
    }
}
