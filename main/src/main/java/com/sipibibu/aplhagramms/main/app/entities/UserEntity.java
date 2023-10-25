package com.sipibibu.aplhagramms.main.app.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.swing.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@AllArgsConstructor
public class UserEntity {

    @Id
    private String email;
    private String name;
    //private ImageIcon image;

    public UserEntity(String email){
        this.email = email;
    }
    public UserEntity(){
    }
}
