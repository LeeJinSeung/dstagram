package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.config.JwtAuthenticationProvider;
import com.landvibe.dstagram.model.JwtRequest;
import com.landvibe.dstagram.model.JwtResponse;
import com.landvibe.dstagram.model.SecurityUser;
import com.landvibe.dstagram.repository.UserRepository;
import com.landvibe.dstagram.config.JwtTokenUtil;
import com.landvibe.dstagram.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        // final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
        final SecurityUser securityUser = userDetailService.loadUserByEmail(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(securityUser);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationProvider.setEncoder(passwordEncoder);
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}

