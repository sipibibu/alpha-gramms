package com.sipibibu.aplhagramms.main.domain;

public enum Gender {
    Male(0),
    Female(1);

    int code;
    Gender(int code){
        this.code=code;
    }

}
