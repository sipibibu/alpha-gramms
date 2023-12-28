package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.FormAnswerDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionAnswerDTO;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.QuestionAnswerEntity;
import com.sipibibu.aplhagramms.main.app.services.FormAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/forms/answers", produces = "application/json")
public class FormAnswerController {

    @Autowired
    private FormAnswersService service;
    @Autowired
    private ObjectMapper objectMapper;
//Text i Scale ne budut rabotat
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FormAnswerDTO dto){
        try {
            service.create(1L,dto);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get/{formId}")
    public ResponseEntity<String> get(@PathVariable Long formId) {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.get(formId).stream()
                            .map(x->new FormAnswerGetDTO(
                                    x.getId(), x.getUserId(),
                                    x.getForm().getId(), x.getQuesions()))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    private record FormAnswerGetDTO(Long id, Long userId, Long formId, List<QuestionAnswerEntity> questions){}
}
