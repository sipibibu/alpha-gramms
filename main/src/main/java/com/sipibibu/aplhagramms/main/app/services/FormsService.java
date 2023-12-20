package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.Assembler;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.InterestsForm;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FormsService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private Assembler assembler;
    public FormEntity create(FormDTO formDTO){
        FormEntity form=assembler.makeForm(formDTO);
        formRepository.save(form);
        return form;
    }
    public void delete(Long id){
        formRepository.deleteById(id);
    }
    public void addQuestion(Long qId,Long fId){
        QuestionEntity question=questionRepository.findById(qId)
                .orElseThrow(()->new RuntimeException("No question with id: "+qId));
        FormEntity form=formRepository.findById(fId)
                .orElseThrow(()->new RuntimeException("No form with id: " +fId));
        form.addQuestion(question);
        formRepository.save(form);
    }
    public void addQuestion(QuestionDTO dto, Long fId){
        FormEntity form=formRepository.findById(fId)
                .orElseThrow(()->new RuntimeException("No form with id: " +fId));
        form.addQuestion(assembler.makeQuestion(dto));
        formRepository.save(form);
    }
    public FormEntity get(Long id){
        return formRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No form with id: "+id));
    }
    public void setStartNEnd(Long formId,LocalDateTime start,LocalDateTime end){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.setDate(start,end);
        formRepository.save(form);
    }
    public void deleteQuestion(Long formId,Long questId){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.deleteQuestion(questId);

        formRepository.save(form);
    }

    public void setDescription(Long formId,String shortDesc,String fullDesc){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.setDescription(shortDesc,fullDesc);
        formRepository.save(form);
    }
    public void setTitle(Long formId,String title){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.setTitle(title);
        formRepository.save(form);
    }
    public List<Long> getInterests(Long formId){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        return form.getInterests().stream().map(InterestsForm::getInterst).toList();
    }
    public void addInterest(Long formId,Long interestId){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addInterest(interestId);
        formRepository.save(form);
    }

    public void deleteInterest(Long formId, Long interestsId){
        FormEntity form=formRepository.findById(formId)
                    .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.deleteInterest(interestsId);
        formRepository.save(form);
    }
    public void setInterests(Long formId, List<Long> interest){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.setInterests(interest);
        formRepository.save(form);
    }
}
