package com.sipibibu.aplhagramms.main.app.dto;

import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

public record FormDTO(String title,
                       String fullDescription,
                       LocalDateTime start,
                       LocalDateTime end,
                       List<QuestionDTO> questions) {
}
