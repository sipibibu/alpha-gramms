package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.FormAnswerDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionAnswerDTO;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.QuestionAnswerEntity;
import com.sipibibu.aplhagramms.main.app.services.FormAnswersService;
import com.sipibibu.aplhagramms.main.infastructure.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/forms/answers", produces = "application/json")
public class FormAnswerController {

    @Autowired
    private FormAnswersService service;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FormAnswerDTO dto){
        try {
            Long userId=userClient.getCurrentId().getBody();
            service.create(userId,dto);
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
