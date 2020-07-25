package com.landvibe.dstagram.controller;

import com.landvibe.dstagram.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.landvibe.dstagram.config.JwtTokenUtil;
import com.landvibe.dstagram.service.JwtUserDetailsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationProvider authenticationProvider;

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

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody Account account) throws Exception {
//        User user = userRepository.findByEmail(account.getEmail())
//                .orElseThrow(() -> new RuntimeException("Email does not exist: " + account.getEmail()));
//        System.out.println("account: " + account.getEmail() + " " + account.getPassword());
//        System.out.println("user: " + user.getEmail() + " " + user.getPassword());
//        if(!user.getPassword().equals(account.getPassword())) {
//            System.out.println("different");
//            throw new RuntimeException("Wrong password");
//        }
//        final UserDetails userDetails = userDetailService.loadUserByUsername(account.getEmail());
//        final String token = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new com.landvibe.dstagram.controller.JwtResponse(token));
//    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}

@Data
@Getter
class JwtRequest {

    private String email;
    private String password;

}

@Data
@AllArgsConstructor
class JwtResponse {

    private String token;

}

