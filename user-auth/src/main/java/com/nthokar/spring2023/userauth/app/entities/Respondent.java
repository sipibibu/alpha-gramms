package com.nthokar.spring2023.userauth.app.entities;

import com.nthokar.spring2023.userauth.app.services.InterestService;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
public class Respondent extends User {
    @ManyToMany
    List<Interest> interests;

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



    public void setInterest(List<Interest> interests) {
        this.interests = interests;
    }

    public void addInterest(Interest interests) {
        this.interests.add(interests);
    }
}
