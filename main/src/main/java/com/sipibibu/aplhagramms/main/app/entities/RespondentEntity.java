package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class RespondentEntity extends UserEntity {
    @ManyToMany
    private List<FormEntity> completedForms;

    @ManyToMany
    private List<FormEntity> subscribedForms;
    public RespondentEntity(String email){
        super(email);
    }
    public RespondentEntity(){

    }
}
