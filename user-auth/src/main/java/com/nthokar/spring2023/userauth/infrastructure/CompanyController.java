package com.nthokar.spring2023.userauth.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.userauth.app.entities.User;
import com.nthokar.spring2023.userauth.app.services.CompanyService;
import com.nthokar.spring2023.userauth.app.services.MyUserDetailsService;
import com.nthokar.spring2023.userauth.app.entities.Company;
import com.nthokar.spring2023.userauth.app.entities.Form;
import com.nthokar.spring2023.userauth.app.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    MyUserDetailsService userService;

    ObjectMapper mapper = new ObjectMapper();

//    @PostMapping("/create")
//    public ResponseEntity<String> create(String title, UserDetails userDetails){
//        try {
//            Manager manager = (Manager) userService.getUser(userDetails.getUsername());
//            companyService.create(title, manager);
//            return ResponseEntity.ok().build();
//        }
//        catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/addForm")
    public ResponseEntity<String> addForm(@RequestParam Long formId, Authentication authentication) {
        companyService.addForm(getManagerCompany(authentication.getName()), new Form(formId));
        return ResponseEntity.ok().build();
    }
    @PutMapping("/setDescription")
    public ResponseEntity<String> setDescription(@RequestBody descriptionDTO descriptionDTO, Authentication authentication) {
        try {
            var company = getManagerCompany(authentication.getName());
            companyService.setDescription(company, descriptionDTO.description);
            return ResponseEntity.ok().body(mapper.writeValueAsString(descriptionDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/setImage")
    public ResponseEntity<String> setImage(@RequestParam String image, Authentication authentication) {
        try {
            var company = getManagerCompany(authentication.getName());
            companyService.setImage(company, image);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/setTitle")
    public ResponseEntity<String> setTitle(@RequestBody titleDTO titleDTO, Authentication authentication) {
        try {
            var company = getManagerCompany(authentication.getName());
            companyService.setTitle(company, titleDTO.title);
            return ResponseEntity.ok().body(mapper.writeValueAsString(titleDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> get(@PathVariable String name) {
        try {
            var company = companyService.get(name);
            return ResponseEntity.ok().body(mapper.writeValueAsString(company.get()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/forms/{name}")
    public ResponseEntity<String> getForms(@PathVariable String name) {
        try {
            var company = companyService.get(name);
            if (company.isEmpty()) throw new RuntimeException("company isn't exist");
            var forms = company.get().getForms();
            return ResponseEntity.ok().body(mapper.writeValueAsString(forms));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getByFormId/{formId}")
    public ResponseEntity<String> getByFormId(@PathVariable Long formId){
        try {
            return  ResponseEntity.ok(mapper.writeValueAsString(companyService.getByForm(formId)));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getCur")
    public ResponseEntity<String> getCur(Authentication authentication) {
        try {
            return ResponseEntity.ok(mapper.writeValueAsString(getManagerCompany(authentication.getName())));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByFormIds")
    public ResponseEntity<String> getByFormIds(@RequestBody List<Long> formId){
        try {
            return  ResponseEntity.ok(mapper.writeValueAsString(companyService.getByForms(formId)));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private Company getManagerCompany(String username) {
        var user = userService.getUser(username);
        Manager manager = (Manager) user;
        return manager.getCompany();
    }
    record descriptionDTO(String description) { }
    record titleDTO(String title) { }
}
