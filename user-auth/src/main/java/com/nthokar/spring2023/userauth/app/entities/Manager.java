package com.nthokar.spring2023.userauth.app.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Manager extends User {
    protected Manager(){
    }
    private Manager(String firstname, String lastname, String email, String password, Set<Role> roles){
        this.firstname = firstname;
        this.lastname = lastname;

        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public static Manager newManager(String firstname, String lastname, String email, String password) {
        var encoder = new BCryptPasswordEncoder();
        var encodedPass = encoder.encode(password);
        return new Manager(firstname, lastname, email, encodedPass, new HashSet<>(){{add(Role.Manager);}});
    }
}
