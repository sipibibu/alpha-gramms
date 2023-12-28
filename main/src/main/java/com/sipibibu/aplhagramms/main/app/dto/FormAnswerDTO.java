package com.sipibibu.aplhagramms.main.app.dto;

import java.util.List;

public record FormAnswerDTO(Long formId, List<QuestionAnswerDTO> questions) {
}
