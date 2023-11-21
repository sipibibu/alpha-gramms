package com.sipibibu.aplhagramms.main.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class Company {
    @Getter
    private Long id;
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    private List<Manager> managers;

}
