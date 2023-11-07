package com.sipibibu.aplhagramms.main.domain.forms;

import jakarta.persistence.Id;

import java.util.Set;

public class Question {
    @Id
    Long id;
    String title;
    String description;
    Set<Answer> answers;
}
