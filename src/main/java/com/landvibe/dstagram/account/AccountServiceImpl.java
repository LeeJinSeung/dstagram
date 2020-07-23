package com.landvibe.dstagram.account;

import com.landvibe.dstagram.model.Account;
import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(Account account) {
        User user = this.userRepository.findByEmail(account.getEmail());
        String token = null;
        if(user == null || !user.getPassword().equals(account.getPassword())) {
            // 해당 eamil을 가진 user가 존재하지 않거나, 비밀번호가 틀렸을 때
            return token;
        }
        return "success";
    }
}
