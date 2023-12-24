package com.nthokar.spring2023.userauth.infrastructure;

import com.nthokar.spring2023.userauth.app.entities.User;
import com.nthokar.spring2023.userauth.app.services.InterestService;
import com.nthokar.spring2023.userauth.app.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    MyUserDetailsService userService;
    @PutMapping("/setImage")
    public ResponseEntity<String> setImage(String base64, Authentication authentication) {
        var user = userService.getUser(authentication.getName());
        userService.setImage(user, base64);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/setEducation")
    public ResponseEntity<String> setEducation(@RequestBody String education, Authentication authentication) {
        var user = userService.getUser(authentication.getName());
        user.setEducation(education);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/setAge")
    public ResponseEntity<String> setAge(@RequestParam Integer age, Authentication authentication) {
        var user = userService.getUser(authentication.getName());
        user.setAge(age);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image")
    public ResponseEntity<String> getImage(@RequestParam String email) {
        try {
            var user = userService.getUser(email);
            return ResponseEntity.ok().body(user.getImage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/setInterests")
    public ResponseEntity<String> setInterest(@RequestParam List<String> interest, Authentication authentication) {
        try {
            var user = userService.getUser(authentication.getName());
            userService.setInterests(user, interest);
            return ResponseEntity.ok().build();
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

    @GetMapping("/")
    public ResponseEntity<User> getCurrent(Authentication authentication) {
        try {
            var user = userService.getUser(authentication.getName());
            user.setPassword("");
            user.setId(null);
            return ResponseEntity.ok().body(user);
        }
        catch (Exception e) {
            throw new RuntimeException();
            //return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
