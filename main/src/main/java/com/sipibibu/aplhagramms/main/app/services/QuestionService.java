package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.repositories.AnswerRepository;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionRepository;
import com.sipibibu.aplhagramms.main.domain.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private FormRepository formRepository;
    public QuestionEntity get(Long id){
        return  repository.findById(id).orElseThrow(()->new RuntimeException("No question with id: "+id));
    }
    public QuestionEntity createTextQuestion(String text, Boolean isReq,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.TextQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        repository.save(quest);
        formRepository.save(form);
        return quest;
    }

    public QuestionEntity createRadioQuestion(String text, Boolean isReq,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.RadioQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        repository.save(quest);
        formRepository.save(form);
        return quest;
    }

    public QuestionEntity createCheckboxQuestion(String text, Boolean isReq,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.CheckboxQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.addQuestion(quest);
        repository.save(quest);
        formRepository.save(form);
        return quest;
    }

    public QuestionEntity createScaleQuestion(String text, Boolean isReq,Long min,Long max,Long step,Long formId){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.ScaleQuestion);
        FormEntity form=formRepository.findById(formId).
                orElseThrow(()->new RuntimeException("No form with id: "+formId));
        repository.save(quest);
        List<AnswerEntity> answers= List.of(
                new AnswerEntity(min.toString(), quest),
                new AnswerEntity(max.toString(), quest),
                new AnswerEntity(step.toString(), quest));
        answerRepository.saveAll(answers);
        quest.addAnswers(answers);
        repository.save(quest);
        form.addQuestion(quest);
        formRepository.save(form);
        return quest;
    }
    public  void delete(Long questId){
        QuestionEntity question=repository.findById(questId)
                .orElseThrow(()->new RuntimeException("No question with id: "+questId));
        answerRepository.deleteAll(question.getAnsVar());
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
