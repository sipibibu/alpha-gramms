package com.nthokar.spring2023.userauth.infrastructure;

import com.nthokar.spring2023.userauth.app.services.CompanyService;
import com.nthokar.spring2023.userauth.app.services.MyUserDetailsService;
import com.nthokar.spring2023.userauth.app.entities.Company;
import com.nthokar.spring2023.userauth.app.entities.Form;
import com.nthokar.spring2023.userauth.app.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<String> setDescription(@RequestParam String description, Authentication authentication) {
        var company = getManagerCompany(authentication.getName());
        companyService.setDescription(company, description);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/setImage")
    public ResponseEntity<String> setImage(@RequestParam String image, Authentication authentication) {
        var company = getManagerCompany(authentication.getName());
        companyService.setImage(company, image);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/setTitle")
    public ResponseEntity<String> setTitle(@RequestParam String title, Authentication authentication) {
        var company = getManagerCompany(authentication.getName());
        companyService.setTitle(company, title);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{title}")
    public ResponseEntity<Company> get(@PathVariable String title) {
        var company = companyService.get(title);
        return company.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private Company getManagerCompany(String username) {
        Manager manager = (Manager) userService.getUser(username);
        return manager.getCompany();
    }
}