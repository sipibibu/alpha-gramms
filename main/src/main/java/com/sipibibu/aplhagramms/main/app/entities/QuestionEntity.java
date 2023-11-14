package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="questions")
@NoArgsConstructor
public class QuestionEntity {
    @Id
    private Long id;
    public  QuestionEntity(Question q){
        this.id=q.getId();
    }
    public Question toQuestion() {
        return null;
    }
}
