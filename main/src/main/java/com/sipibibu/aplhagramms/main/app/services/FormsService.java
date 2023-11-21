package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.CompanyEntity;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.repositories.CompanyRepository;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FormsService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    public FormEntity create(String title, Long companyId, String shortDisc, String fullDisc,
                             LocalDateTime createTime,LocalDateTime start,LocalDateTime end){
        CompanyEntity company=companyRepository.findById(companyId)
                .orElseThrow(()->new RuntimeException("No such company with id: "+companyId));
        FormEntity form=new FormEntity(title,shortDisc,fullDisc,company,start,end);
        formRepository.save(form);
        return form;
    }
    public void addQuestion(Long qId,Long fId){
        QuestionEntity question=questionRepository.findById(qId)
                .orElseThrow(()->new RuntimeException("No question with id: "+qId));
        FormEntity form=formRepository.findById(fId).orElseThrow(()->new RuntimeException("No form with id: " +fId));
        form.addQuestion(question);
        form.setUpdatedAt(LocalDateTime.now());
        formRepository.save(form);
    }
    public FormEntity get(Long id){
        return formRepository.findById(id).orElseThrow(()->new RuntimeException("No form with id: "+id));
    }
}
