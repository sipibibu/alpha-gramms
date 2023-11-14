/*
package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.UserEntity;
import com.sipibibu.aplhagramms.main.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired UserRepository repo;

    public Optional<UserEntity> getById(String id){
        return repo.findById(id);
    }
    public UserEntity register(UserEntity user) {
        return repo.save(user);
    }
}
*/
