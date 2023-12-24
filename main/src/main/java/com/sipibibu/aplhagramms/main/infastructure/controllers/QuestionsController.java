package com.sipibibu.aplhagramms.main.infastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/quest", produces = "application/json")
public class QuestionsController {

    @Autowired
    private QuestionService service;
    ObjectMapper objectMapper=new ObjectMapper();

    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.get(id)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/createText")
    public ResponseEntity<String> createTextQuestion(String text, Boolean isReq,Long formId){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.createTextQuestion(text,isReq,formId)));

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/createRadio")
    public ResponseEntity<String> createRadioQuestion(String text, Boolean isReq,Long formId){
        try{
            return ResponseEntity.ok(objectMapper.
                    writeValueAsString(service.createRadioQuestion(text,isReq,formId)));

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/createCheckbox")
    public ResponseEntity<String> createCheckboxQuestion(String text, Boolean isReq,Long formId){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.createCheckboxQuestion(text,isReq,formId)));

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/createScale")
    public ResponseEntity<String> createScaleQuestion(String text, Boolean isReq,
                                                      Long min,Long max,Long step,Long formId){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.createScaleQuestion(text,isReq,min,max,step,formId)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{questId}")
    public ResponseEntity<String> delete(@PathVariable Long questId){
        try{
            service.delete(questId);
            return ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setReq/{questId}")
    public ResponseEntity<String> setReq(@PathVariable Long questId,@RequestParam Boolean req){
        try{
            service.setReq(questId,req);
            return ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setText/{questId}")
    public ResponseEntity<String> setText(@PathVariable Long questId,@RequestBody String text){
        try{
            service.setText(questId,text);
            return ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
