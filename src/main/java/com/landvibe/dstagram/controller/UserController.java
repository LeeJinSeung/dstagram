package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private Map<String, User> cachedUsers = new HashMap<>();


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createPost(@RequestBody User user) {

        if(cachedUsers.containsValue(user.getEmail()) || cachedUsers.containsValue((user.getNickname()))) {
            return null;
        }
        else {
            user.setUid(UUID.randomUUID().toString().replace("-", ""));
            cachedUsers.put(user.getNickname(), user);
            return user;
        }
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@RequestBody User user) {
        if(cachedUsers.containsValue(user.getEmail()) && cachedUsers.containsValue((user.getNickname()))) {
            cachedUsers.remove(user.getNickname());
        }
        else {
           // error
            throw new RuntimeException(user + " doesn't exist");
        }
    }

    @GetMapping("/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public User getOnePost(@PathVariable String nickname) {
        if(cachedUsers.containsKey(nickname)) {
            return cachedUsers.get(nickname);
        }
        else {
            throw new RuntimeException(nickname + " doesn't exist");
        }
    }
}
