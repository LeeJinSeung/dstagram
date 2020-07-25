package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.Account;
import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class AccountServiceImpl implements AccountService {
//    @Autowired
//    private UserRepository userRepository;
//
//    private JsonWebToken jsonWebToken;
//
//    public AccountServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//
//    public String login(Account account) {
//        User user = this.userRepository.findByEmail(account.getEmail());
//
//        if(user != null && user.getPassword().equals(account.getPassword())) {
//            // 로그인 성공
//            String token = "";
//            return token;
//        }
//        else if(user == null) {
//            // email 존재 x
//            return null;
//        }
//        else {
//            // password 불일치
//            return null;
//        }
//    }
//}
