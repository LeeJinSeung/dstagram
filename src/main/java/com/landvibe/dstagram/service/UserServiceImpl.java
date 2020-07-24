package com.landvibe.dstagram.service;

import com.landvibe.dstagram.repository.UserRepository;
import com.landvibe.dstagram.model.Profile;
import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        User user1 = this.userRepository.findByEmail(user.getEmail());
        if(user1 != null) {
            if(user1.getNickname().equals(user.getNickname())) {
                // nickname 중복
                return null;
            }
            else {
                // email 중복
                return null;
            }

        }
        else {
            return this.userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(User user) {
        // token을 이용하여 삭제하는 방식으로 변경해야함.
        if (this.userRepository.findById(user.getUid()).isPresent()) {
            this.userRepository.deleteById(user.getUid());
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
