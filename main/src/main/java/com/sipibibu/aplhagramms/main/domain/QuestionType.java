package com.sipibibu.aplhagramms.main.domain;

public enum QuestionType {
    TextQuestion("TextQuestion"),
    RadioQuestion("RadioQuestion"),
    CheckboxQuestion("CheckboxQuestion"),
    ScaleQuestion("ScaleQuestion");

    final String type;
    QuestionType(String type){
        this.type=type;
    }

}
