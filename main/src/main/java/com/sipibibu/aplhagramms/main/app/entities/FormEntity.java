package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name="forms")
public class FormEntity {
    @Id
    @GeneratedValue
    @Column(name="id",nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "companies")
    private CompanyEntity company;
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

    public FormEntity(Long id, String title, String shortDescription,
                String fullDescription, CompanyEntity company,
                LocalDateTime startingAt, LocalDateTime closingAt,
                LocalDateTime updatedAt, List<QuestionEntity> questions){
        this.id=id;
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.company=company;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        setUpdatedAt(updatedAt);
        this.questions=questions;
    }
    public FormEntity(Long id,String title,CompanyEntity company,
                LocalDateTime startingAt,LocalDateTime closingAt){
        this.id=id;
        setTitle(title);
        this.company=company;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        this.questions=new ArrayList<>();
    }
    public FormEntity(String title,String shortDescription,
                String fullDescription,CompanyEntity company,
                LocalDateTime startingAt,LocalDateTime closingAt){
        setTitle(title);
        this.shortDescription=shortDescription;
        this.fullDescription=fullDescription;
        this.company=company;
        this.startingAt=startingAt;
        this.closingAt=closingAt;
        this.questions=new ArrayList<QuestionEntity>();
    }

    public void setTitle(@NotNull String newTitle) throws  RuntimeException{
        if (!newTitle.replaceAll("[\\W\\n]", "").isBlank())
            title = newTitle.strip();
        else
            throw new RuntimeException("Incorrect title");
    }
    public void addQuestion(QuestionEntity q){
        this.questions.add(q);
    }
    public void removeQuestion(Long qId){
        questions.removeIf(x-> x.getId().equals(id));
    }
    public void setUpdatedAt(LocalDateTime upd){
        if(upd.isAfter(startingAt) && upd.isBefore(closingAt))
            updatedAt=upd;
        else
            throw new RuntimeException("Wrong upd date");
    }
}
