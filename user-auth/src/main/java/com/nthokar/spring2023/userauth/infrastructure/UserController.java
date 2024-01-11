package com.nthokar.spring2023.userauth.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.userauth.app.entities.User;
import com.nthokar.spring2023.userauth.app.services.MyUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    MyUserDetailsService userService;
    ObjectMapper mapper = new ObjectMapper();

    @PutMapping("/setImage")
    public ResponseEntity<String> setImage(String base64, Authentication authentication) {
        var user = userService.getUser(authentication.getName());
        userService.setImage(user, base64);
        return ResponseEntity.ok().build();

    }

    @SneakyThrows
    @PutMapping("/setEducation")
    public ResponseEntity<String> setEducation(@RequestBody educationDTO educationDTO, Authentication authentication) {
        try {
            var user = userService.getUser(authentication.getName());
            userService.setEducation(user, educationDTO.education);
            return ResponseEntity.ok().body(mapper.writeValueAsString(educationDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/setAge")
    public ResponseEntity<String> setAge(@RequestBody ageDTO ageDTO, Authentication authentication) {
        try {
            Integer ageInt = Integer.parseInt(ageDTO.age);
            var user = userService.getUser(authentication.getName());
            userService.setAge(user, ageInt);
            user.setAge(ageInt);
            return ResponseEntity.ok().body(mapper.writeValueAsString(ageDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/image")
    public ResponseEntity<String> getImage(@RequestBody emailDTO emailDTO) {
        try {
            var user = userService.getUser(emailDTO.email);
            return ResponseEntity.ok().body(user.getImage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/setInterests")
    public ResponseEntity<String> setInterest(@RequestBody interestsDTO interestsDTO, Authentication authentication) {
        try {
            var user = userService.getUser(authentication.getName());
            userService.setInterests(user, interestsDTO.interests);
            return ResponseEntity.ok().body(mapper.writeValueAsString(interestsDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user, Authentication authentication) {
        try {
            var userOld = userService.getUser(authentication.getName());
            userService.update(userOld, user);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<User> getCurrent(Authentication authentication) {
        try {
            var user = userService.getUser(authentication.getName());
            user.setPassword("");
            user.setId(null);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            throw new RuntimeException();
            //return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getId")
    public ResponseEntity<Long> getCurrentId(Authentication authentication) {
        try {
            var user = userService.getUser(authentication.getName());
            return ResponseEntity.ok().body(user.getId());
        } catch (Exception e) {
            throw new RuntimeException();
            //return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCompany")
    public ResponseEntity<String> getCurrentCompany(Authentication authentication) {
        try {
            var company = userService.getCompany(authentication.getName());
            return ResponseEntity.ok().body(mapper.writeValueAsString(company));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    record educationDTO (String education) {};
    record ageDTO (String age) {};
    record emailDTO (String email) {};
    record interestsDTO (List<String> interests) {};
}
