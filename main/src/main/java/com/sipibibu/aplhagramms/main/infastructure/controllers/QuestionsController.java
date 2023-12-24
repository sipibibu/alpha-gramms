package com.sipibibu.aplhagramms.main.infastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Text;

@RestController
@RequestMapping(value = "/quest", produces = "application/json")
public class QuestionsController {

    @Autowired
    private QuestionService service;
    ObjectMapper objectMapper=new ObjectMapper();

    public record ScaleDto(String text, Boolean isReq,
                           Long min,Long max,Long step,Long formId){}
    public record QuestionDto(String text, Boolean isReq,Long formId){}
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
    public ResponseEntity<String> createTextQuestion(@RequestBody QuestionDto dto){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.createTextQuestion(dto.text,dto.isReq,dto.formId)));

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/createRadio")
    public ResponseEntity<String> createRadioQuestion(@RequestBody QuestionDto dto){
        try{
            return ResponseEntity.ok(objectMapper.
                    writeValueAsString(service.createRadioQuestion(dto.text,dto.isReq,dto.formId)));

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/createCheckbox")
    public ResponseEntity<String> createCheckboxQuestion(@RequestBody QuestionDto dto){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.createCheckboxQuestion(dto.text,dto.isReq,dto.formId)));

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/createScale")
    public ResponseEntity<String> createScaleQuestion(@RequestBody ScaleDto dto){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    service.createScaleQuestion(dto.text,dto.isReq,dto.min,dto.max,dto.step,dto.formId)));
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
