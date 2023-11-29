package com.nthokar.spring2023.userauth.app;

import com.nthokar.spring2023.userauth.app.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}