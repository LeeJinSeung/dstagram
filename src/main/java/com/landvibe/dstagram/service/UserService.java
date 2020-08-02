package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.Profile;
import com.landvibe.dstagram.model.User;

public interface UserService {
    User createUser(User user) throws Exception;
    void deleteUser(String token) throws Exception;
    Profile getProfile(String nickname) throws Exception;
}
