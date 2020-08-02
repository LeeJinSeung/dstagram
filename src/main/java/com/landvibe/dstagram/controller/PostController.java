package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostResponse;
import com.landvibe.dstagram.service.PostService;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.http.HttpHeaders;
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
    public List<PostResponse> getPosts(@RequestParam(defaultValue="0") int skip, @RequestParam(defaultValue = "30") int limit) {
        return this.postService.getPosts(skip, limit);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @RequestBody PostResponse postResponse) {
        String tokenValue = token.replace("Bearer ", "").trim();
        this.postService.createPost(tokenValue, postResponse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @PathVariable int id, @RequestBody Post post) {
        String tokenValue = token.replace("Bearer ", "").trim();
        return this.postService.updatePost(tokenValue, id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @PathVariable int id) {
        String tokenValue = token.replace("Bearer ", "").trim();
        this.postService.deletePost(tokenValue, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPost(@PathVariable int id) {
        return this.postService.getPost(id);
    }
}
