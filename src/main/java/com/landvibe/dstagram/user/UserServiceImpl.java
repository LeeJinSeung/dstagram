package com.landvibe.dstagram.user;

import com.landvibe.dstagram.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        if (this.userRepository.findById(user.getUid()).isPresent()) {
            this.userRepository.deleteById(user.getUid());
        } else {
            throw new RuntimeException("Not found post: " + user.getUid());
        }
    }

    @Override
    public User getProfile(String nickname) {
//        if(this.userRepository.findById(nickname).isPresent()) {
//            return this.userRepository.findById(nickname);
//        }
//        else {
//            throw new RuntimeException("Not found post: " + nickname);
//        }
        return null;
    }
}
