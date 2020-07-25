package com.landvibe.dstagram.service;

import com.landvibe.dstagram.repository.UserRepository;
import com.landvibe.dstagram.model.Profile;
import com.landvibe.dstagram.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    final PasswordEncoder encode;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encode) {
        this.userRepository = userRepository;
        this.encode = encode;
    }

    @Override
    public User createUser(User user) {
        if (!this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            user.setPassword(encode.encode(user.getPassword()));
            return this.userRepository.save(user);
        }
        else
            return null;
    }

    @Override
    public void deleteUser(User user) {
        // token을 이용하여 삭제하는 방식으로 변경해야함.
        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            this.userRepository.deleteByEmail(user.getEmail());
        } else {
            throw new RuntimeException("Not found post: " + user.getUid());
        }
    }

    @Override
    public User getProfile(String nickname) {
        User user = this.userRepository.findByNickname(nickname);
        if(user == null) {
            // 존재하지 않는 user일 경우
            // throw new NotFoundException("Not found user: " + nickname);
            return null;
        }
        else {
            Profile profile = new Profile();
            profile.setEmail(user.getEmail());
            profile.setName(user.getName());
            profile.setNickname(user.getNickname());

            return user;
        }
    }
}
