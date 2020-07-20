package com.landvibe.dstagram.controller;


import com.landvibe.dstagram.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/login")
public class AccountController {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody Account account) {

        return "";
    }
}
