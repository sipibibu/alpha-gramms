package com.sipibibu.aplhagramms.main.app.dto;

import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public record FormDTO(String title,
                       String fullDescription,
                      ZonedDateTime start,
                       ZonedDateTime end,
                       List<QuestionDTO> questions) {
}
