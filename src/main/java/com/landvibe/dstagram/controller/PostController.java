package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostResponse;
import com.landvibe.dstagram.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPosts(@RequestParam int skip, @RequestParam int limit) {
        return this.postService.getPosts(skip, limit);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostResponse postResponse) {
        this.postService.createPost(postResponse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return this.postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@RequestHeader String token, @PathVariable int id) {
        this.postService.deletePost(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPost(@PathVariable int id) {
        return this.postService.getPost(id);
    }
}