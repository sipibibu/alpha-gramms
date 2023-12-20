package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.InterestsForm;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import com.sipibibu.aplhagramms.main.app.services.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/form", produces = "application/json")
public class FormController {
    @Autowired
    private FormsService formsService;
    ObjectMapper objectMapper=new ObjectMapper();
    public FormController(FormsService fService){
        objectMapper.findAndRegisterModules();
        formsService=fService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FormDTO formDTO){
        try{
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body( objectMapper.writeValueAsString(
                            formsService.create(formDTO)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/addExist")
    public ResponseEntity<String> addExistingQuest(Long qId,Long fId){
        try{
            formsService.addQuestion(qId, fId);
            return  ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/add")
    public ResponseEntity<String> addQuest(QuestionDTO dto, Long fid){
        try{
            formsService.addQuestion(dto,fid);
            return  ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/get")
    public ResponseEntity<String> get(Long id){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(formsService.get(id)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(Long formId){
        try{
            formsService.delete(formId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setDate")
    public ResponseEntity<String> setStartNEnd(Long formId,LocalDateTime start,LocalDateTime end){
        try{
            formsService.setStartNEnd(formId, start, end);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }

    @PutMapping("/deleteQuestion")
    public ResponseEntity<String> deleteQuestion(Long formId,Long questId){
        try{
            formsService.deleteQuestion(formId, questId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/setDesc")
    public ResponseEntity<String> setDescription(Long formId,String shortDesc,String fullDesc){
        try{
            formsService.setDescription(formId, shortDesc, fullDesc);
            return ResponseEntity.ok("ok");

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setTitle")
    public ResponseEntity<String> setTitle(Long formId,String title){
        try {
            formsService.setTitle(formId, title);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getInterests")
    public ResponseEntity<String> getInterests(Long formId){
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(formsService.getInterests(formId)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/addInterest")
    public ResponseEntity<String> addInterest(Long formId,Long interestId){
        try {
            formsService.addInterest(formId, interestId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteInterest")
    public ResponseEntity<String> deleteInterest(Long formId, Long interestsId){
        try {
            formsService.deleteInterest(formId, interestsId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setInterests")
    public ResponseEntity<String> setInterests(Long formId, List<Long> interest){
        try {
            formsService.setInterests(formId, interest);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
