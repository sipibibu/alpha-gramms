package com.nthokar.spring2023.userauth.app.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Type;

import java.util.Set;

@Data
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")
//@JsonSubTypes({
//        @Type(value = Manager.class, name = "manager"),
//})
@Getter
public abstract class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name="firstName")
    protected String firstName;

    @Column(name="lastName")
    protected String lastName;

    @Column(name = "age")
    protected Integer age;

    @Column(name = "education")
    protected String education;


    @Column(name="email")
    protected String email;

    @Column(name="password")
    protected String password;

    //BASE64
    @Column(name="image")
    protected String image;

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected Set<Role> roles;
}

