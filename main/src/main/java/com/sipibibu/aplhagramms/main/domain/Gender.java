package com.sipibibu.aplhagramms.main.domain;

public enum Gender {
    Male(0),
    Female(1);

    final int code;
    Gender(int code){
        this.code=code;
    }

}
