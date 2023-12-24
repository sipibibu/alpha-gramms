package com.nthokar.spring2023.userauth.app.entities;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.usertype.UserType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@AllArgsConstructor
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")
public class Manager extends User {

    @Setter
    @OneToOne
    Company company;

    protected Manager() {
    }
    private Manager(String email, String firstName, String lastName, String password, Set<Role> roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public static Manager newManager(String email, String firstName, String lastName, String password) {
        var encoder = new BCryptPasswordEncoder();
        var encodedPass = encoder.encode(password);

        return new Manager(email, firstName, lastName, encodedPass, new HashSet<>(){{add(Role.Manager);}});
    }
}
