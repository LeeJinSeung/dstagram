package com.landvibe.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int uid;

    @Column(nullable = false)
    private String email;

    @Column
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String password;
}
