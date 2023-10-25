package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class CompanyEntity {
    @Id
    private String id;
    private String name;
    @OneToMany
    private List<UserEntity> managers;
}
