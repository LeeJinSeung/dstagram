package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.*;
import com.landvibe.dstagram.repository.ImageRepository;
import com.landvibe.dstagram.repository.PostRepository;
import com.landvibe.dstagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Profile getProfile(String nickname) {
        User user = this.userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException(nickname));

        List<Post> posts = this.postRepository.findByUid(user.getUid())
                .orElseThrow(() -> new UsernameNotFoundException(Integer.toString(user.getUid())));

        List<Integer> pid = new ArrayList<>();
        List<String> imageUrl = new ArrayList<>();

        for(int i=0;i<posts.size();i++) {
            int finalI = i;
            List<Image> imageList = this.imageRepository.findByPid(posts.get(i).getPid())
                    .orElseThrow(() -> new UsernameNotFoundException(Integer.toString(posts.get(finalI).getPid())));

            pid.add(posts.get(i).getPid());
            imageUrl.add(imageList.get(0).getImageUrl());
        }


        Profile profile = new Profile(user, pid, imageUrl);

        return profile;

    }


}
