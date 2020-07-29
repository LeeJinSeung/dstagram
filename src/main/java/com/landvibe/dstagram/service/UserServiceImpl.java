package com.landvibe.dstagram.service;

import com.landvibe.dstagram.config.JwtTokenUtil;
import com.landvibe.dstagram.exception.ResourceConflictException;
import com.landvibe.dstagram.exception.ParameterNotFoundException;
import com.landvibe.dstagram.model.*;
import com.landvibe.dstagram.repository.ImageRepository;
import com.landvibe.dstagram.repository.PostRepository;
import com.landvibe.dstagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    final PasswordEncoder encode;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encode) {
        this.userRepository = userRepository;
        this.encode = encode;
    }

    @Override
    public User createUser(User user) throws Exception {
        if (this.userRepository.findByEmail(user.getEmail()).isPresent() && this.userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new ResourceConflictException("Duplicate email and nickname");
        }
        else if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResourceConflictException("Duplicate email");
        }
        else if(this.userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new ResourceConflictException("Duplicate nickname");
        }
        else {
            user.setPassword(encode.encode(user.getPassword()));
            return this.userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(String token) throws Exception {
        int uid = jwtTokenUtil.getUserIdFromToken(token);

        if (this.userRepository.findById(uid).isPresent()) {
            this.userRepository.deleteById(uid);
            List<Post> posts = this.postRepository.findByUid(uid)
                    .orElse(new ArrayList<>());
            for(int i=0;i<posts.size();i++) {
                this.imageRepository.deleteAllByPid(posts.get(i).getPid());
            }
            this.postRepository.deleteAllByUid(uid);
        } else {
            throw new RuntimeException("Not found post: " + uid);
        }
    }

    @Override
    public Profile getProfile(String nickname) throws Exception {
        User user = this.userRepository.findByNickname(nickname)
                .orElseThrow(() -> new ParameterNotFoundException("Not Found"));

        List<Post> posts = this.postRepository.findByUid(user.getUid())
                .orElse(new ArrayList<>());

        List<Integer> pid = new ArrayList<>();
        List<String> imageUrl = new ArrayList<>();

        for(int i=0;i<posts.size();i++) {
            List<Image> imageList = this.imageRepository.findByPid(posts.get(i).getPid())
                    .orElse(new ArrayList<>());

            pid.add(posts.get(i).getPid());
            imageUrl.add(imageList.get(0).getImageUrl());
        }

        return new Profile(user, pid, imageUrl);

    }


}
