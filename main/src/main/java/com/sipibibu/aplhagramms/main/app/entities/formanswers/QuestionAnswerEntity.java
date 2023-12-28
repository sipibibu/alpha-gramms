package com.sipibibu.aplhagramms.main.app.entities.formanswers;

import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="QuestionsAnswers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionAnswerEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "form")
    private FormAnswerEntity form;
    @ManyToOne
    @JoinColumn(name = "question")
    private QuestionEntity question;
    @Column(name = "answer")
    private String text;

    public QuestionAnswerEntity(FormAnswerEntity form,QuestionEntity question,String text){
        this.form=form;
        this.question=question;
        this.text=text;
    }

}
