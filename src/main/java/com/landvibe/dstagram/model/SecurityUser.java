package com.landvibe.dstagram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    public SecurityUser(String email, String password, int uid, String nickname, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.uid = uid;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    private String email;
    private String password;
    private int uid;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
