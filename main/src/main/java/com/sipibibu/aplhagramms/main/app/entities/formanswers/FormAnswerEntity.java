package com.sipibibu.aplhagramms.main.app.entities.formanswers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="FormAnswers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FormAnswerEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="userId")
    private Long userId;
    @ManyToOne
    @JoinColumn(name="form")
    private FormEntity form;
    @OneToMany
    @JsonManagedReference
    @JoinColumn(name="questions")
    private List<QuestionAnswerEntity> quesions;

    public FormAnswerEntity(Long userId, FormEntity form){
        this.userId=userId;
        this.form=form;
        this.quesions=new ArrayList<>();
    }
    public void addQuestion(QuestionAnswerEntity question){
        quesions.add(question);
    }


}
