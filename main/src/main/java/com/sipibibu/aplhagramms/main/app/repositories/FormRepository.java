package com.sipibibu.aplhagramms.main.app.repositories;


import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormRepository  extends CrudRepository<FormEntity,Long> {

}
