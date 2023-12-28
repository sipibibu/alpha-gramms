package com.sipibibu.aplhagramms.main.app.repositories;

import com.sipibibu.aplhagramms.main.app.dto.FormAnswerDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.FormAnswerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormAnswersRepository extends CrudRepository<FormAnswerEntity,Long> {

    List<FormAnswerEntity> findAllByForm(FormEntity form);

}
