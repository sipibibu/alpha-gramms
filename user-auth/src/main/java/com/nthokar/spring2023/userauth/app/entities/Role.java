package com.nthokar.spring2023.userauth.app.entities;

import jakarta.persistence.*;
import lombok.Data;

public enum Role {
    Respondent,
    Manager
//    @Id
//    @Column(name="id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name="role_name")
//    private String roleName;
}