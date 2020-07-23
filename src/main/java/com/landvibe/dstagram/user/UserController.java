package com.landvibe.dstagram.user;

import com.landvibe.dstagram.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
