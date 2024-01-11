package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.IForm;
import com.sipibibu.aplhagramms.main.domain.IQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
@Table(name="forms")
public class FormEntity implements IForm {
    @Id
    @GeneratedValue
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "shortDescription")
    private String shortDescription;
    @Column(name = "fullDescription")
    private String fullDescription;
    @Column(name = "startingAt")
    private ZonedDateTime startingAt;
    @Column(name = "closingAt")
    private ZonedDateTime closingAt;
    @Column(name = "updatedAt")
    private ZonedDateTime updatedAt;
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="questions")
    private List<QuestionEntity> questions;
    @OneToMany
    @JoinColumn(name="interests")
    private List<InterestsForm> interests;

    public FormEntity(Long id, String title, String shortDescription,
                String fullDescription,
                      ZonedDateTime startingAt, ZonedDateTime closingAt,
                      ZonedDateTime updatedAt, List<QuestionEntity> questions,List<InterestsForm> interests){
        this.id=id;
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        setUpdatedAt(updatedAt);
        this.questions=questions;
        this.interests=interests;
    }
    public FormEntity(Long id,String title,ZonedDateTime created,
                      ZonedDateTime startingAt,ZonedDateTime closingAt){
        this.id=id;
        setTitle(title);
        this.updatedAt=created;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        this.questions=new ArrayList<>();
    }
    public FormEntity(String title,String shortDescription,
                String fullDescription,ZonedDateTime created,
                      ZonedDateTime startingAt,ZonedDateTime closingAt,List<QuestionEntity> questions){
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.updatedAt=created;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        //ne udalyat'
        if (Objects.isNull(questions)) this.questions = new ArrayList<>();
        else this.questions=questions;
    }
    public FormEntity(String title,String shortDescription,
                      String fullDescription,ZonedDateTime created){
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.updatedAt=created;
    }
    @Override
    public void setTitle(String newTitle) throws  RuntimeException{
        title = newTitle.strip();
        setUpdatedAt(ZonedDateTime.now());
    }
    @Override
    public void addQuestion(@NonNull IQuestion q){
        this.questions.add((QuestionEntity) q);
        setUpdatedAt(ZonedDateTime.now());
    }
    @Override
    public void removeQuestion(Long qId){
        questions.removeIf(x-> x.getId().equals(id));
    }
    @Override
    public void setUpdatedAt(@NonNull ZonedDateTime upd){
        updatedAt=upd;
    }
    @Override
    public void setDate(ZonedDateTime start,ZonedDateTime end){
        if(start.isBefore(end)){
            this.startingAt=start;
            this.closingAt=end;
            setUpdatedAt(ZonedDateTime.now());
        }
        else throw new RuntimeException("Start is after end");
    }
    @Override
    public void deleteQuestion(Long questId){
        questions.removeIf(x->x.getId().equals(questId));
        setUpdatedAt(ZonedDateTime.now());
    }
    @Override
    public void setDescription(String shortDesc,String fullDesc){
        shortDescription=shortDesc;
        fullDescription=fullDesc;
        setUpdatedAt(ZonedDateTime.now());
    }
    @Override
    public  void addInterest(InterestsForm interest){
        if(this.interests.stream().map(InterestsForm::getInterst).noneMatch(x->x.equals(interest.getId())))
            this.interests.add(interest);
    }
    @Override
    public  void deleteInterest(Long id){
        interests.removeIf(x-> x.getInterst().equals(id));
    }

    @Override
    public void setInterests(List<InterestsForm> interests){
        this.interests=interests;
    }
}
