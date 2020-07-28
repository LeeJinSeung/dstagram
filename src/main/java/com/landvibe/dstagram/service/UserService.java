package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.Profile;
import com.landvibe.dstagram.model.User;

public interface UserService {
    User createUser(User user);
    void deleteUser(User user);
    Profile getProfile(String nickname);
}
