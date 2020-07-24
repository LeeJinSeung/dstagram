package com.landvibe.dstagram.jwt;


import com.landvibe.dstagram.model.User;
import com.landvibe.dstagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private String HEADER_TOKEN_KEY = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationException {
        User user = userRepository.findByEmail(request.getHeader("email"));
        if(user == null) {
            throw new RuntimeException("user is not exist");
        }

        String givenToken = request.getHeader(HEADER_TOKEN_KEY);
        verifyToken(givenToken, user.getToken());

        return true;
    }

    private void verifyToken(String givenToken, String membersToken) throws AuthenticationException {
        if (!givenToken.equals(membersToken)) {
            throw new AuthenticationException("Incorrect token.");
        }

        jwtUtil.verifyToken(givenToken);
    }

}
