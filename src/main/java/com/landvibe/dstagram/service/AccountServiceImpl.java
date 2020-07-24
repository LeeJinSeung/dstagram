package com.landvibe.dstagram.service;

import com.landvibe.dstagram.jwt.JwtUtil;
import com.landvibe.dstagram.model.Account;
import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.repository.UserRepository;
import com.landvibe.dstagram.service.AccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public JwtUtil jwtUtil;

    public AccountServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }



    public String login(Account account) {
        User user = this.userRepository.findByEmail(account.getEmail());

        if(user != null && user.getPassword().equals(account.getPassword())) {
            // 로그인 성공
            return user.getToken();
        }
        else if(user == null) {
            // email 존재 x
            return null;
        }
        else {
            // password 불일치
            return null;
        }
    }
}
