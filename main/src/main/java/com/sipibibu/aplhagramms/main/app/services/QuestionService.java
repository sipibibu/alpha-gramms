package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionRepository;
import com.sipibibu.aplhagramms.main.domain.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;

    public QuestionEntity createTextQuestion(String text, Boolean isReq){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.TextQuestion);
        repository.save(quest);
        return quest;
    }

    public QuestionEntity createRadioQuestion(String text, Boolean isReq){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.RadioQuestion);
        repository.save(quest);
        return quest;
    }

    public QuestionEntity createCheckboxQuestion(String text, Boolean isReq){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.CheckboxQuestion);
        repository.save(quest);
        return quest;
    }

    public QuestionEntity createScaleQuestion(String text, Boolean isReq,Long min,Long max,Long step){
        QuestionEntity quest=new QuestionEntity(text,isReq,QuestionType.ScaleQuestion);
        quest.addAnswer(new AnswerEntity(min.toString(),quest));
        quest.addAnswer(new AnswerEntity(max.toString(),quest));
        quest.addAnswer(new AnswerEntity(step.toString(),quest));
        repository.save(quest);
        return quest;
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

}
