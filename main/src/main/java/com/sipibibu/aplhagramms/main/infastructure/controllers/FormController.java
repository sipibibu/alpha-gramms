package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

// Интересы - а надо +
@RestController
@RequestMapping(value = "/forms", produces = "application/json")
public class FormController {
    @Autowired
    private FormService formService;
    @Value("${services.gateway}")
    String gatewayUrl;
    ObjectMapper objectMapper=new ObjectMapper();
    public FormController(FormService fService){
        objectMapper.findAndRegisterModules();
        formService=fService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FormDTO formDTO){
        try{
            FormEntity form = formService.create(formDTO);
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Bearer "+authentication.getCredentials().toString());
            HttpEntity<String> requestRest = new HttpEntity<String>( headers);

            restTemplate.postForEntity("http://"+gatewayUrl+"/company/addForm?formId="+form.getId(),
                    requestRest,String.class);
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body( objectMapper.writeValueAsString(form));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.get(id)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<String> getAll() {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getAll()));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
        @PutMapping("/addExistQuestion/{fId}")
    public ResponseEntity<String> addExistingQuest( Long questId, @PathVariable Long fId){
        try{
            formService.addQuestion(questId, fId);
            return  ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/addQuestion/{formId}")
    public ResponseEntity<String> addQuest(@RequestBody QuestionDTO dto,@PathVariable Long formId){
        try{
            formService.addQuestion(dto,formId);
            return  ResponseEntity.ok("Ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            formService.delete(id);
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
            formService.setStartNEnd(id, start, end);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }

    @PutMapping("/deleteQuestion/{formId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long formId, Long questId){
        try{
            formService.deleteQuestion(formId, questId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/setDesc{id}")
    public ResponseEntity<String> setDescription(@PathVariable Long id,String shortDesc,String fullDesc){
        try{
            formService.setDescription(id, shortDesc, fullDesc);
            return ResponseEntity.ok("ok");

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setTitle")
    public ResponseEntity<String> setTitle(Long formId,String title){
        try {
            formService.setTitle(formId, title);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getInterests")
    public ResponseEntity<String> getInterests(Long formId){
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getInterests(formId)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/addInterest")
    public ResponseEntity<String> addInterest(Long formId,Long interestId){
        try {
            formService.addInterest(formId, interestId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteInterest")
    public ResponseEntity<String> deleteInterest(Long formId, Long interestsId){
        try {
            formService.deleteInterest(formId, interestsId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/setInterests")
    public ResponseEntity<String> setInterests(Long formId, List<Long> interest){
        try {
            formService.setInterests(formId, interest);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
