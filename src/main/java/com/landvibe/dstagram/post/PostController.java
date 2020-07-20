package com.landvibe.dstagram.post;

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

    private PostService postService;

    public PostController() {
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return this.postService.getPosts();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return this.postService.createPost(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return this.postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        this.postService.deletePost(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getOnePost(@PathVariable int id) {
        return this.postService.getOnePost(id);
    }
}
