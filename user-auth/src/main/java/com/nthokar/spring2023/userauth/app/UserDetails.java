package com.nthokar.spring2023.userauth.app;

import com.nthokar.spring2023.userauth.app.entities.Role;
import com.nthokar.spring2023.userauth.app.entities.User;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    User user;

    public UserDetails (User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {authorities.add(new SimpleGrantedAuthority(role.name()));}
        return authorities;
    }

    public String getImage() {return user.getImage(); }

    @Override
    public String getPassword() {return user.getPassword();}

    @Override
    public String getUsername() {return user.getEmail();}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}