package com.sipibibu.aplhagramms.main.infastructure.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipibibu.aplhagramms.main.app.dto.CompanyDTO;
import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.InterestsForm;
import com.sipibibu.aplhagramms.main.app.services.FormService;
import com.sipibibu.aplhagramms.main.infastructure.clients.CompanyClient;
import com.sipibibu.aplhagramms.main.infastructure.clients.InterestsClient;
import com.sipibibu.aplhagramms.main.infastructure.clients.UserClient;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//Che delat s checkbox
//Zapisatsya i poluchit oprosi na kotorye zapisan
@RestController
@RequestMapping(value = "/forms", produces = "application/json")
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private InterestsClient interestsClient;
    @Autowired
    private UserClient userClient;

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
            var res=formService.get(id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getByCompanyName/{name}")
    public ResponseEntity<String> getByCompanyName(@PathVariable String name){
        try{
            var company=objectMapper.readValue(companyClient.get(name).getBody(),
                    new TypeReference<CompanyFull>() {});
            var forms=formService.getAllByIds(company.forms.stream().map(x->x.id).toList()).
                    stream().map(x->new FormGetDTO(
                            x.getId(),x.getTitle(),
                            company.id(),company.title(),
                            x.getShortDescription(),x.getFullDescription(),
                            x.getStartingAt(),x.getClosingAt(),x.getInterests()
                            .stream().map(InterestsForm::getInterst).toList())).toList();

            return ResponseEntity.ok(objectMapper.writeValueAsString(forms));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/getCurCompany")
    public ResponseEntity<String> getCurComp(){
        try{
            var company=objectMapper.readValue(companyClient.getCur().getBody()
                    ,new TypeReference<CompanyFull>(){});
            var forms=formService.getAllByIds(company.forms.stream().map(x->x.id).toList())
                    .stream().map(x->new FormGetDTO(x.getId(),x.getTitle(),
                            company.id(),company.title(),
                            x.getShortDescription(),x.getFullDescription(),
                            x.getStartingAt(),x.getClosingAt(),x.getInterests()
                            .stream().map(InterestsForm::getInterst).toList()));
            return ResponseEntity.ok(objectMapper.writeValueAsString(forms));
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
                            form.getStartingAt(),form.getClosingAt(),form.getInterests()
                            .stream().map(InterestsForm::getInterst).toList())));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/getAvailable")
    public ResponseEntity<String> getAvailable(){
        try{

            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getAvailable()));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getAvailableById")
    @ExceptionHandler
    public ResponseEntity<Long[]> getAvailableById(@RequestBody List<Long> formsIds){
        return ResponseEntity.ok((formService.getAvailable(formsIds).stream().map(x -> x.getId()).toArray(Long[]::new)));
    }
    @GetMapping("/getCompletedById")
    public ResponseEntity<String> getCompletedById(@RequestBody List<Long> formsIds){
        try{

            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getCompleted(formsIds)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getCompleted")
    public ResponseEntity<String> getCompleted(){
        try{

            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getCompleted()));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getUpcoming")
    public ResponseEntity<String> getUpcoming(){
        try{

            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getUpcoming()));
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
                            x.getStartingAt(),x.getStartingAt(),x.getInterests()
                            .stream().map(InterestsForm::getInterst).toList());
                return null;
            }).toList();
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
        @PutMapping("/addExistQuestion/{fId}")
    public ResponseEntity<String> addExistingQuest( @RequestBody Long questId, @PathVariable Long fId){
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
                                               @RequestBody ZonedDateTime start,
                                               @RequestBody ZonedDateTime end){
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
    @PutMapping("/setTitle/{formId}")
    public ResponseEntity<String> setTitle(@PathVariable  Long formId,@RequestBody String title){
        try {
            formService.setTitle(formId, title);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getInterests/{formId}")
    public ResponseEntity<String> getInterests(@PathVariable Long formId){
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(formService.getInterests(formId)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{formId}/addInterest")
    public ResponseEntity<String> addInterest(@PathVariable Long formId,@RequestParam Long interestId){
        try {
            boolean exist=objectMapper.readValue(interestsClient.isExist(interestId).getBody(),
                    new TypeReference<Boolean>() {});
            if(!exist)
                throw new RuntimeException("No interest with id: "+interestId);
            formService.addInterest(formId, interestId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @DeleteMapping("/{formId}/deleteInterest")
    public ResponseEntity<String> deleteInterest(@PathVariable Long formId,@RequestBody Long interestsId){
        try {
            formService.deleteInterest(formId, interestsId);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/{formId}/setInterests")
    public ResponseEntity<String> setInterests(@PathVariable Long formId, @RequestBody InterestsListDTO interests){
        try {
            var interestsExists=objectMapper.readValue(
                    interestsClient.isExistMany(interests.values).getBody(),
                    new TypeReference<List<Long>>() {});
            formService.setInterests(formId, interestsExists);
            return ResponseEntity.ok("ok");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    record FormId(Long  id){}

    record FormGetDTO(Long id, String title, Long companyId, String companyName,
                      String shortDescription, String fullDescription,
                      ZonedDateTime startingAt, ZonedDateTime closingAt,List<Long> interests){}
    record CompanyFull(Long id,
            String title,
            String description,
            String image, List<FormId> forms){}
    record InterestsListDTO(Long[] values){}
}
