package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
