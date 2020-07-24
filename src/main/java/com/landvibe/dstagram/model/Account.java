package com.landvibe.dstagram.model;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class Account {
    @Column
    private String email;

    @Column
    private String password;
}
