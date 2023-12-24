package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.services.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(formsService.get(id)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<String> getAll() {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(formsService.getAll()));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
        @PutMapping("/addExistQuestion/{fId}")
    public ResponseEntity<String> addExistingQuest( Long questId, @PathVariable Long fId){
        try{
            formsService.addQuestion(questId, fId);
            return  ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/addQuestion/{formId}")
    public ResponseEntity<String> addQuest(@RequestBody QuestionDTO dto,@PathVariable Long formId){
        try{
            formsService.addQuestion(dto,formId);
            return  ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            formsService.delete(id);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setDate/{id}")
    public ResponseEntity<String> setStartNEnd(@PathVariable Long id,
                                                LocalDateTime start,
                                                LocalDateTime end){
        try{
            formsService.setStartNEnd(id, start, end);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }

    @PutMapping("/deleteQuestion/{formId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long formId, Long questId){
        try{
            formsService.deleteQuestion(formId, questId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/setDesc{id}")
    public ResponseEntity<String> setDescription(@PathVariable Long id,String shortDesc,String fullDesc){
        try{
            formsService.setDescription(id, shortDesc, fullDesc);
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
