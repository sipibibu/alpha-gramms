package com.sipibibu.aplhagramms.main.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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
