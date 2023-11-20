package com.sipibibu.aplhagramms.main.domain;

import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Set;

@Getter
public class Question {
    Long id;
    String title;
    String description;
    Set<Answer> answers;
}
