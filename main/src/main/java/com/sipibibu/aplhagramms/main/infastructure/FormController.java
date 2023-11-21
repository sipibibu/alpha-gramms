package com.sipibibu.aplhagramms.main.infastructure;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.services.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;

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
    public ResponseEntity<String> create(@RequestParam String title, @RequestParam Long companyId){
        try{
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body( objectMapper.writeValueAsString(
                            formsService.create(title, companyId,"","",
                            LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1))));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/add")
    public ResponseEntity<String> addQuest(Long qId,Long fId){
        try{
            formsService.addQuestion(qId, fId);
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

}
