package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private Map<Integer, Post> cachedPosts = new HashMap<>();

    public PostController() {
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return new ArrayList<>(cachedPosts.values());
    }




//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Post createPost(@RequestBody Post post) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        post.setCreated(localDateTime);
//        post.setUpdated(localDateTime);
//
//        cachedPosts.put(post.getId(), post);
//        return post;
//    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        LocalDateTime localDateTime = LocalDateTime.now();
        post.setCreated(localDateTime);
        post.setUpdated(localDateTime);

        cachedPosts.put(post.getId(), post);
        return post;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        LocalDateTime localDateTime = LocalDateTime.now();
        post.setUpdated(localDateTime);
        post.setCreated(cachedPosts.get(id).getCreated());
        if(cachedPosts.containsKey(post.getId())) {
            // id가 있을 때
            cachedPosts.put(id, post);
        }
        else {
            // id가 없을 때 error
            throw new RuntimeException(id + " doesn't exist");
        }

        return post;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        if(cachedPosts.containsKey(id)) {
            // id가 있을 때
            cachedPosts.remove(id);
        }
        else {
            // id가 없을 때 error
            throw new RuntimeException(id + " doesn't exist");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getOnePost(@PathVariable int id) {
        if(cachedPosts.containsKey(id)) {
            return cachedPosts.get(id);
        }
        else {
            throw new RuntimeException(id + " doesn't exist");
        }
    }
}
