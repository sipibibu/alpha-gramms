package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "managers")
public class ManagerEntity {
    @Id
    @GeneratedValue
    private Long id;
}
