package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class FormEntity {
    @Id
    private String id;
    @ManyToOne
    private CompanyEntity company;

    private String title;
    private String shortDescription;
    private String fullDescription;

    private Date startingAt;
    private Date closingAt;
    private Date updatedAt;
}
