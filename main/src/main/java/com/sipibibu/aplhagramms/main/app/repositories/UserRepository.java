package com.sipibibu.aplhagramms.main.app.repositories;

import com.sipibibu.aplhagramms.main.app.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
