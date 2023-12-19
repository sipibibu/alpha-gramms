package com.sipibibu.aplhagramms.main.app.dto;

import com.sipibibu.aplhagramms.main.domain.QuestionType;
import lombok.Getter;

import java.util.List;

public record QuestionDTO(
    String questionText,
    Boolean isReq,
    List<AnswerDTO> ansVar,
     QuestionType type){

}
