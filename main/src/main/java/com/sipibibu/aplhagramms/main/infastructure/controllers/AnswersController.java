package com.sipibibu.aplhagramms.main.infastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/answers", produces = "application/json")
public class AnswersController {

    @Autowired
    private AnswerService service;

    ObjectMapper objectMapper=new ObjectMapper();
    public ResponseEntity<String> get(Long id){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.get(id)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity<String> setText(Long id,String text){
        try{
            service.setText(id,text);
            return ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
