package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.repository.UserRepository;
import com.landvibe.dstagram.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    final PasswordEncoder encode;

    public UserController(UserService userService, PasswordEncoder encode) {
        this.userService = userService;
        this.encode = encode;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestBody User user) {
        this.userService.deleteUser(user);
    }

    @GetMapping("/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public User getProfile(@PathVariable String nickname) {
        return this.userService.getProfile(nickname);
    }
}
