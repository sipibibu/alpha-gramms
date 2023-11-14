package com.sipibibu.aplhagramms.main.app.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.swing.*;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String email;
    private String name;
    //private ImageIcon image;

    public UserEntity(String email){
        this.email = email;
    }

}
