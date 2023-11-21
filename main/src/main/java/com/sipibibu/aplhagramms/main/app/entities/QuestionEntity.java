package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.IQuestion;
import com.sipibibu.aplhagramms.main.domain.models.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="questions")
@NoArgsConstructor
public class QuestionEntity implements IQuestion {
    @Id
    private Long id;

}
