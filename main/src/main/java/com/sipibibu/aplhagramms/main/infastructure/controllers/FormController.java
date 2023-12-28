package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.CompanyDTO;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.services.FormService;
import com.sipibibu.aplhagramms.main.infastructure.clients.CompanyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


// Интересы - а надо +
//Zapisatsya i poluchit oprosi na kotorye zapisan
@RestController
@RequestMapping(value = "/forms", produces = "application/json")
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private CompanyClient companyClient;

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

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Bearer "+authentication.getCredentials().toString());
            HttpEntity<String> requestRest = new HttpEntity<String>( headers);
            FormEntity form = formService.create(formDTO);

            restTemplate.postForEntity(gatewayUrl+"/company/addForm?formId="+form.getId(),
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

    @GetMapping("/getShort/{id}")
    public ResponseEntity<String> getShort(@PathVariable Long id){
        try{
            var form=formService.get(id);
            var company=objectMapper.readValue(companyClient.getByFormId(id).getBody()
                    ,new TypeReference<CompanyDTO>(){});
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    new FormGetDTO(form.getId(),form.getTitle(),
                            company.id(), company.name(),
                            form.getShortDescription(), form.getFullDescription(),
                            form.getStartingAt(),form.getClosingAt())));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<String> getAll() {
        try {
            var forms=formService.getAll();
            HashMap<Long,CompanyDTO> companies=objectMapper
                    .readValue(
                            companyClient.getByFormIds(forms.stream()
                            .map(FormEntity::getId).toList()).getBody(),
                            new TypeReference<HashMap<Long, CompanyDTO>>() {});

            var res=forms.stream().map(x->
            {
                var tmp=companies.get(x.getId());
                if(Objects.nonNull(tmp))
                    return new FormGetDTO(x.getId(),x.getTitle(),tmp.id(),tmp.name()
                            ,x.getShortDescription(),x.getFullDescription(),
                            x.getStartingAt(),x.getStartingAt());
                return null;
            }).toList();
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
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

    record FormGetDTO(Long id, String title, Long companyId, String companyName,
                      String shortDescription, String fullDescription,
                      LocalDateTime startingAt, LocalDateTime closingAt){}
}
