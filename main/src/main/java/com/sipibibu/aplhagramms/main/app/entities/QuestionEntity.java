package com.sipibibu.aplhagramms.main.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sipibibu.aplhagramms.main.domain.IQuestion;
import com.sipibibu.aplhagramms.main.domain.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="questions")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity implements IQuestion {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Setter
    @Column(name="qtext")
    private String questionText;
    @Setter
    @Column(name="req")
    private Boolean isReq;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="answerVars")
    @JsonManagedReference
    private List<AnswerEntity> ansVar;
    @Column(name="type")
    private QuestionType type;

    public QuestionEntity(String questionText,Boolean isReq,QuestionType type){
        this.questionText=questionText;
        this.isReq=isReq;
        ansVar= new ArrayList<>();
        this.type=type;
    }

    public void addAnswer(@NotNull AnswerEntity answer){
        ansVar.add(answer);
    }
    public void addAnswers(@NotNull List<AnswerEntity> answers){
        ansVar.addAll(answers);
    }

    public void deleteAnswer(Long answerId){
        ansVar.removeIf(x->x.getId().equals(id));
    }


}
