package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.Assembler;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.InterestsForm;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.repositories.AnswerRepository;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.InterestsFormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private InterestsFormRepository interestsFormRepository;
    @Autowired
    private Assembler assembler;
    public FormEntity create(FormDTO formDTO){
        FormEntity form=assembler.makeForm(formDTO);
        formRepository.save(form);
        return form;
    }
    //+company id
    public FormEntity get(Long id){

        var res=formRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No form with id: "+id));
        return res;
    }
    public  List<FormEntity> getAll(){
        return StreamSupport.stream(formRepository.findAll().spliterator(),false).toList();
    }
    //zapisatsy na opros + poluchit oprosi na kotorie zapisalsya
    public void delete(Long id){
        FormEntity form=formRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No form with id: " +id));
        if(!form.getQuestions().isEmpty()) {
            for (var i : form.getQuestions()) {
                if (!i.getAnsVar().isEmpty())
                    answerRepository.deleteAll(i.getAnsVar());
            }
            questionRepository.deleteAll(form.getQuestions());
        }
        formRepository.deleteById(id);
    }
    public void addQuestion(Long qId,Long fId){
        QuestionEntity question=questionRepository.findById(qId)
                .orElseThrow(()->new RuntimeException("No question with id: "+qId));
        FormEntity form=formRepository.findById(fId)
                .orElseThrow(()->new RuntimeException("No form with id: " +fId));
        form.addQuestion(question);
        if(Objects.nonNull(question.getAnsVar()))
            answerRepository.saveAll(question.getAnsVar());
        formRepository.save(form);
    }
    public void addQuestion(QuestionDTO dto, Long fId){
        FormEntity form=formRepository.findById(fId)
                .orElseThrow(()->new RuntimeException("No form with id: " +fId));
        var question = assembler.makeQuestion(dto);
        answerRepository.saveAll(question.getAnsVar());
        questionRepository.save(question);
        form.addQuestion(question);
        formRepository.save(form);
    }

    public void setStartNEnd(Long formId, ZonedDateTime start, ZonedDateTime end){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.setDate(start,end);
        formRepository.save(form);
    }
    public void deleteQuestion(Long formId,Long questId){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        form.getQuestions().stream().map(QuestionEntity::getId).filter(x-> Objects.equals(x, questId))
                .findAny().orElseThrow(()->new RuntimeException("No question with id: "+questId));
        form.deleteQuestion(questId);
        formRepository.save(form);
        questionRepository.deleteById(questId);
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
        return form.getInterests().stream().map(InterestsForm::getId).toList();
    }
    public void addInterest(Long formId,Long interestId){
        FormEntity form=formRepository.findById(formId)
                .orElseThrow(()->new RuntimeException("No form with id: "+formId));
        var interest=new InterestsForm(interestId);
        form.addInterest(interest);
        interestsFormRepository.save(interest);
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
        var interests=interest.stream().map(InterestsForm::new).toList();
        interestsFormRepository.saveAll(interests);
        form.setInterests(interests);
        formRepository.save(form);
    }
    public List<FormEntity> getAllByIds(List<Long> formIds){
        return StreamSupport.stream(formRepository.findAllById(formIds).spliterator(),false).toList();
    }
    public List<FormEntity> getAvailable(){
        return getAll().stream().filter(x->x.getClosingAt().isAfter(ZonedDateTime.now()) &&
                x.getStartingAt().isBefore(ZonedDateTime.now())).toList();
    }
    public List<FormEntity> getAvailable(List<Long> formsIds){
        return getAllByIds(formsIds).stream().filter(x->x.getClosingAt().isAfter(ZonedDateTime.now()) &&
                x.getStartingAt().isBefore(ZonedDateTime.now())).toList();
    }
    public List<FormEntity> getCompleted(){
        return getAll().stream().filter(x->x.getClosingAt().isBefore(ZonedDateTime.now())).toList();
    }
    public List<FormEntity> getCompleted(List<Long> formsIds){
        return getAllByIds(formsIds).stream().filter(x->x.getClosingAt().isBefore(ZonedDateTime.now())).toList();
    }
    public List<FormEntity> getUpcoming(){
        return getAll().stream().filter(x->
                x.getStartingAt().isAfter(ZonedDateTime.now())).toList();
    }


}
