package com.nthokar.spring2023.userauth.app.repos;


import com.nthokar.spring2023.userauth.app.entities.Interest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InterestRepository extends CrudRepository<Interest, Long> {
    boolean existsByName(String name);
    Optional<Interest> findByName(String name);
}
