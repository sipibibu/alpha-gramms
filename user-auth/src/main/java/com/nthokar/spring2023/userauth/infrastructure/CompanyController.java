package com.nthokar.spring2023.userauth.infrastructure;

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
        var company = getManagerCompany(authentication.getName());
        companyService.setDescription(company, descriptionDTO.description);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/setImage")
    public ResponseEntity<String> setImage(@RequestParam String image, Authentication authentication) {
        var company = getManagerCompany(authentication.getName());
        companyService.setImage(company, image);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/setTitle")
    public ResponseEntity<String> setTitle(@RequestBody titleDTO titleDTO, Authentication authentication) {
        var company = getManagerCompany(authentication.getName());
        companyService.setTitle(company, titleDTO.title);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        var company = companyService.get(name);
        return company.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/forms/{name}")
    public ResponseEntity<List<Form>> getForms(@PathVariable String name) {
        try {
            var company = companyService.get(name);
            if (company.isEmpty()) throw new RuntimeException("company isn't exist");
            var forms = company.get().getForms();
            return ResponseEntity.ok().body(forms);
        } catch (Exception e) {
            throw new RuntimeException();
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
