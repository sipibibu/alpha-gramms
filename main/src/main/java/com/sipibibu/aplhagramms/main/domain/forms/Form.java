package com.sipibibu.aplhagramms.main.domain.forms;

import jakarta.persistence.Id;

public class Form {
    @Id
    Long id;
    String title;
    String description;

}
