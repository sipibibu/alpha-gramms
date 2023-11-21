package com.sipibibu.aplhagramms.main.domain.models;

import lombok.Getter;

import java.util.Set;

@Getter
public class Question {
    Long id;
    String title;
    String description;
    Set<Answer> answers;
}
