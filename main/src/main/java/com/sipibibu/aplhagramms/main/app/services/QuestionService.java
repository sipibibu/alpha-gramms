package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionRepository;
import com.sipibibu.aplhagramms.main.domain.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;
    @Autowired
    FormRepository formRepository;
    public QuestionEntity get(Long id){
        return  repository.findById(id).orElseThrow(()->new RuntimeException("No question with id: "+id));
    }
    public QuestionEntity createTextQuestion(String text, Boolean isReq,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.TextQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        formRepository.save(form);
        return repository.save(quest);
    }

    public QuestionEntity createRadioQuestion(String text, Boolean isReq,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.RadioQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        formRepository.save(form);
        return repository.save(quest);
    }

    public QuestionEntity createCheckboxQuestion(String text, Boolean isReq,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.CheckboxQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        formRepository.save(form);
        return repository.save(quest);
    }

    public QuestionEntity createScaleQuestion(String text, Boolean isReq,Long min,Long max,Long step,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.ScaleQuestion);
        quest.addAnswer(new AnswerEntity(min.toString(),quest));
        quest.addAnswer(new AnswerEntity(max.toString(),quest));
        quest.addAnswer(new AnswerEntity(step.toString(),quest));
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        formRepository.save(form);
        return repository.save(quest);
    }
    public  void delete(Long questId){
        QuestionEntity question=repository.findById(questId)
                .orElseThrow(()->new RuntimeException("No question with id: "+questId));
        repository.delete(question);
    }
    public void setReq(Long questId,Boolean req){
        QuestionEntity question=repository.findById(questId)
                .orElseThrow(()->new RuntimeException("No question with id: "+questId));
        question.setIsReq(req);
        repository.save(question);
    }
    public void setText(Long questId,String text){
        QuestionEntity question=repository.findById(questId)
                .orElseThrow(()->new RuntimeException("No question with id: "+questId));
        question.setQuestionText(text);
        repository.save(question);
    }

}
