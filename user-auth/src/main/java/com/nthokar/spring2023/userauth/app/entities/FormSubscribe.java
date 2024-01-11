package com.nthokar.spring2023.userauth.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormSubscribe {
    @Id
    @Getter
    private Long id;

    public Boolean isAvailable;
}
