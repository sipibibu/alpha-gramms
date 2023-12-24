package com.nthokar.spring2023.userauth.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String title;
    String description;
    String image;

    @OneToMany
    List<Form> forms;

    public void addForm(Form form) {
        forms.add(form);
    }

    public void addInterests(Interest interest) {

    }

    public Company(String title) {
        this.title = title;
    }
}
