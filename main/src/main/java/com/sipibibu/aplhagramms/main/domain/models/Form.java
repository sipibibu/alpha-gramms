package com.sipibibu.aplhagramms.main.domain.models;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class Form {
    private Long id;
    private String title;
    @Setter
    private String shortDescription;
    @Setter
    private String fullDescription;
    private Company company;
    @Setter
    private LocalDateTime startingAt;
    @Setter
    private LocalDateTime closingAt;
    private LocalDateTime updatedAt;
    private List<Question> questions;

}
