package com.sipibibu.aplhagramms.main;

import com.sipibibu.aplhagramms.main.app.dto.FormAnswerDTO;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionAnswerDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.FormAnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.QuestionAnswerEntity;
import com.sipibibu.aplhagramms.main.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Assembler {

    @Autowired
    private FormAnswersRepository formAnswersRepository;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private FormRepository formRepository;

    public QuestionEntity makeQuestion(QuestionDTO dto){
        QuestionEntity quest = new QuestionEntity(dto.questionText(),dto.isReq(),dto.type());
        questionRepository.save(quest);
        List<AnswerEntity> ans = new ArrayList<>();
        if (Objects.nonNull(dto.ansVar())) {
            for (var a : dto.ansVar()){
                var answer=new AnswerEntity(a.text(), quest);
                answerRepository.save(answer);
                ans.add(answer);
            }
        }
        quest.addAnswers(ans);
        questionRepository.save(quest);
        return  quest;
    }

    public List<QuestionEntity> makeQuestions(List<QuestionDTO> dtos) {
        List<QuestionEntity> questions = new ArrayList<>();
        if (Objects.isNull(dtos)) return questions;
        for (var dto:dtos) {
            questions.add(makeQuestion(dto));
        }
        return questions;
    }
    public FormEntity makeForm(FormDTO dto){
        FormEntity form = new FormEntity(dto.title(), "",
                dto.fullDescription(), ZonedDateTime.now(),dto.start(),
                dto.end(), makeQuestions(dto.questions()));
        return form;
    }
    public QuestionAnswerEntity makeQuestionAnswer(FormAnswerEntity form,QuestionAnswerDTO dto, Long formId){
        QuestionEntity question=questionRepository.findById(dto.questionId())
                .orElseThrow(()->new RuntimeException("No question with id: "+dto.questionId()));
        FormEntity formEntity=formRepository.findById(formId).get();
        if(formEntity.getQuestions().stream().noneMatch(x->x.getId()==dto.questionId()))
            throw new RuntimeException("No question with id: "+dto.questionId()+" in form with id: "+form.getId());
        return  new QuestionAnswerEntity(form,question, dto.text());
    }
    public FormAnswerEntity makeFormAnswer(Long userId,FormAnswerDTO dto){
        FormEntity form=formRepository.findById(dto.formId())
                .orElseThrow(()->new RuntimeException("No form with id: "+dto.formId()));
        FormAnswerEntity formAnswer=new FormAnswerEntity(userId,form);
        formAnswersRepository.save(formAnswer);
        if(Objects.nonNull(dto.questions()))
            for(var quest:dto.questions()) {
                var tmp=makeQuestionAnswer(formAnswer, quest, form.getId());
                questionAnswerRepository.save(tmp);
                formAnswer.addQuestion(tmp);
            }
        return formAnswer;
    }
}
