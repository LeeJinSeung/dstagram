package com.landvibe.dstagram.user;

import com.landvibe.dstagram.model.User;

public interface UserService {
    User createUser(User user);
    void deleteUser(User user);
    User getProfile(String nickname);
}
