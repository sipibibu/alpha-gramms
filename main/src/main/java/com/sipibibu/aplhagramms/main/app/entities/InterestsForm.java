package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="interests")
@NoArgsConstructor
@AllArgsConstructor
public class InterestsForm {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="interestId")
    private Long interst;

    public InterestsForm(Long interst){
        this.interst=interst;
    }
}
