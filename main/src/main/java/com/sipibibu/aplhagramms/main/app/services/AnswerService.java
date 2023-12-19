package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    public AnswerEntity get(Long id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("No answer with id: "+id));
    }

    public void setText(Long id,String text){
        AnswerEntity answer=repository.findById(id).orElseThrow(()->new RuntimeException("No answer with id: "+id));
        answer.setText(text);
        repository.save(answer);
    }

}
