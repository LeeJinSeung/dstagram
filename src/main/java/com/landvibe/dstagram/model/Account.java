package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class Account {
    @Column
    private String email;

    @Column
    private String password;

}
