package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.User;

public interface UserService {
    User createUser(User user);
    void deleteUser(User user);
    User getProfile(String nickname);
}
