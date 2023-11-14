package com.nthokar.spring2023.userauth.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Respondent extends User {
    protected Respondent(){
    }
    private Respondent(String email, String password, Set<Role> roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public static Respondent newRespondent(String email, String password) {
        var encoder = new BCryptPasswordEncoder();
        var encodedPass = encoder.encode(password);
        return new Respondent(email, encodedPass, new HashSet<>(){{add(Role.Respondent);}});
    }
}