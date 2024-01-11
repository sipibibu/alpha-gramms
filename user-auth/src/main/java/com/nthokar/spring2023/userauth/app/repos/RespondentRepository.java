package com.nthokar.spring2023.userauth.app.repos;

import com.nthokar.spring2023.userauth.app.entities.FormSubscribe;
import com.nthokar.spring2023.userauth.app.entities.Respondent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RespondentRepository extends CrudRepository<Respondent, Long> {
    List<Respondent> findByUpcomingContaining(FormSubscribe formSubscribe);
}
