package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int uid;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String token;
}
