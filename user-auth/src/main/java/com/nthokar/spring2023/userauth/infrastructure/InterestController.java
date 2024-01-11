package com.nthokar.spring2023.userauth.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.userauth.app.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interests")
public class InterestController {
    @Autowired InterestService interestService;
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/")
    public ResponseEntity<String> getAll() {
        try {
            var interests = interestService.getAllInterests();
            return ResponseEntity.ok().body(mapper.writeValueAsString(interests));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{name}")
    public ResponseEntity<String> get(@PathVariable String name) {
        try {
            var interests = interestService.get(name);

            return ResponseEntity.ok().body(mapper.writeValueAsString(interests));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/isExist/{id}")
    public ResponseEntity<String> isExist(@PathVariable Long id) {
        try {
            var interests = interestService.isExist(id);
            return ResponseEntity.ok().body(mapper.writeValueAsString(interests));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/isExistMany")
    public ResponseEntity<String> isExistMany(@RequestBody Long[] ids) {
        try {
            var interests = interestService.exists(ids);
            return ResponseEntity.ok().body(mapper.writeValueAsString(interests));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
