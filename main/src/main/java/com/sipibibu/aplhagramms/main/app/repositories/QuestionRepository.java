package com.sipibibu.aplhagramms.main.app.repositories;

import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {
}
