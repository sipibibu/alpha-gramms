package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.IForm;
import com.sipibibu.aplhagramms.main.domain.IQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name="forms")
public class FormEntity implements IForm {
    @Id
    @GeneratedValue
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "managerId")
    private Long manager;
    @Column(name = "title")
    private String title;
    @Column(name = "shortDescription")
    private String shortDescription;
    @Column(name = "fullDescription")
    private String fullDescription;
    @Column(name = "startingAt")
    private LocalDateTime startingAt;
    @Column(name = "closingAt")
    private LocalDateTime closingAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @OneToMany
    @JoinColumn(name="questions")
    private List<QuestionEntity> questions;
    @OneToMany
    @JoinColumn(name="interests")
    private List<InterestsForm> interests;

    public FormEntity(Long id, String title, String shortDescription,
                String fullDescription, Long manager,
                LocalDateTime startingAt, LocalDateTime closingAt,
                LocalDateTime updatedAt, List<QuestionEntity> questions,List<InterestsForm> interests){
        this.id=id;
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.manager=manager;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        setUpdatedAt(updatedAt);
        this.questions=questions;
        this.interests=interests;
    }
    public FormEntity(Long id,String title,Long manager,LocalDateTime created,
                LocalDateTime startingAt,LocalDateTime closingAt){
        this.id=id;
        setTitle(title);
        this.manager=manager;
        this.updatedAt=created;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        this.questions=new ArrayList<>();
    }
    public FormEntity(String title,String shortDescription,
                String fullDescription,Long manager,LocalDateTime created,
                LocalDateTime startingAt,LocalDateTime closingAt,List<QuestionEntity> questions){
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.manager=manager;
        this.updatedAt=created;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        this.questions=questions;
    }
    public FormEntity(String title,String shortDescription,
                      String fullDescription,Long manager,LocalDateTime created){
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.manager=manager;
        this.updatedAt=created;
    }
    @Override
    public void setTitle(String newTitle) throws  RuntimeException{
        if (!newTitle.replaceAll("[\\W\\n]", "").isBlank()) {
            title = newTitle.strip();
            setUpdatedAt(LocalDateTime.now());
        }
        else
            throw new RuntimeException("Incorrect title");
    }
    @Override
    public void addQuestion(@NonNull IQuestion q){
        this.questions.add((QuestionEntity) q);
        setUpdatedAt(LocalDateTime.now());
    }
    @Override
    public void removeQuestion(Long qId){
        questions.removeIf(x-> x.getId().equals(id));
    }
    @Override
    public void setUpdatedAt(@NonNull LocalDateTime upd){
        if(upd.isAfter(startingAt) && upd.isBefore(closingAt))
            updatedAt=upd;
        else
            throw new RuntimeException("Wrong upd date");
    }
    @Override
    public void setDate(LocalDateTime start,LocalDateTime end){
        if(start.isBefore(end)){
            this.startingAt=start;
            this.closingAt=end;
            setUpdatedAt(LocalDateTime.now());
        }
        else throw new RuntimeException("Start is after end");
    }
    @Override
    public void deleteQuestion(Long questId){
        questions.removeIf(x->x.getId().equals(questId));
        setUpdatedAt(LocalDateTime.now());
    }
    @Override
    public void setDescription(String shortDesc,String fullDesc){
        shortDescription=shortDesc;
        fullDescription=fullDesc;
        setUpdatedAt(LocalDateTime.now());
    }
    @Override
    public  void addInterest(Long id){
        if(this.interests.stream().map(InterestsForm::getInterst).noneMatch(x->x.equals(id)))
            this.interests.add(new InterestsForm(id,this));
    }
    @Override
    public  void deleteInterest(Long id){
        interests.removeIf(x-> x.getInterst().equals(id));
    }

    @Override
    public void setInterests(List<Long> interests){
        for(var i:interests){
            addInterest(i);
        }
    }
}
