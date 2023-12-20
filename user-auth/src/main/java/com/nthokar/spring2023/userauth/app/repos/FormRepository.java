package com.nthokar.spring2023.userauth.app.repos;

import com.nthokar.spring2023.userauth.app.entities.Form;
import org.springframework.data.repository.CrudRepository;

public interface FormRepository extends CrudRepository<Form, Long> {
}
