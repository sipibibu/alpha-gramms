package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.IAnswer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="answers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity implements IAnswer {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="title")
    @Setter
    private String text;
    @ManyToOne
    @JoinColumn(name = "question")
    private QuestionEntity question;

    public AnswerEntity(String text,QuestionEntity question){
        this.text=text;
        this.question=question;
    }
}
