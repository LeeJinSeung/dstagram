package com.landvibe.dstagram.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class JwtRequest {

    private String email;
    private String password;
}
