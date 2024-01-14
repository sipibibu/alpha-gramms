package com.nthokar.spring2023.userauth.app.entities;

import com.nthokar.spring2023.userauth.app.services.InterestService;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Getter
@Entity
public class Respondent extends User {
    @ManyToMany
    List<Interest> interests;
    @ManyToMany
    List<FormSubscribe> upcoming;
    protected Respondent(){
    }
    private Respondent(String email, String firstName, String lastName, String password, Set<Role> roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Respondent newRespondent(String email, String firstName, String lastName, String password) {
        var encoder = new BCryptPasswordEncoder();
        var encodedPass = encoder.encode(password);
        return new Respondent(email, firstName, lastName, encodedPass, new HashSet<>(){{add(Role.Respondent);}});
    }


    public void setInterest(List<Interest> interests) {
        this.interests = interests;
    }

    public void addInterest(Interest interests) {
        this.interests.add(interests);
    }


    public void subscribe(FormSubscribe form) {
        this.upcoming.add(form);
    }

    public void unsubscribe(Long formID) {
        for (var form:upcoming) {
            if (form.getId().equals(formID)) {
                upcoming.remove(form);
                break;
            }
        }
    }
}
