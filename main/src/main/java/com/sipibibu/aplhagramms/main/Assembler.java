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
        List<AnswerEntity> ans = new ArrayList<>();
        if (Objects.nonNull(dto.ansVar())) {
            for (var a : dto.ansVar())
                ans.add(new AnswerEntity(a.text(), quest));
        }
        quest.addAnswers(ans);
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
                dto.fullDescription(), LocalDateTime.now(),dto.start(),
                dto.end(), makeQuestions(dto.questions()));
        return form;
    }
    public QuestionAnswerEntity makeQuestionAnswer(FormAnswerEntity form,QuestionAnswerDTO dto){
        QuestionEntity question=questionRepository.findById(dto.questionId())
                .orElseThrow(()->new RuntimeException("No question with id: "+dto.questionId()));
        return  new QuestionAnswerEntity(form,question, dto.text());
    }
    public FormAnswerEntity makeFormAnswer(Long userId,FormAnswerDTO dto){
        FormEntity form=formRepository.findById(dto.formId())
                .orElseThrow(()->new RuntimeException("No form with id: "+dto.formId()));
        FormAnswerEntity formAnswer=new FormAnswerEntity(userId,form);
        if(Objects.nonNull(dto.questions()))
            for(var quest:dto.questions())
                formAnswer.addQuestion(makeQuestionAnswer(formAnswer,quest));
        return formAnswer;
    }
}
