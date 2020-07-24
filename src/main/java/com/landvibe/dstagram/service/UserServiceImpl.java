package com.landvibe.dstagram.service;

import com.landvibe.dstagram.jwt.JwtUtil;
import com.landvibe.dstagram.repository.UserRepository;
import com.landvibe.dstagram.model.Profile;
import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void verifyDuplicatedUser(String email) {
        if (userRepository.findByEmail(email) != null)
            throw new RuntimeException("This ID is already exist.");
    }

    @Override
    public User createUser(User user) {
        System.out.println(user.getEmail());
        verifyDuplicatedUser(user.getEmail());
        System.out.println("create user");

        user.setToken(jwtUtil.createToken());
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        // token을 이용하여 삭제하는 방식으로 변경해야함.
        if (this.userRepository.findByEmail(user.getEmail()) != null) {
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
