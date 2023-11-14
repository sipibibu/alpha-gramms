package com.sipibibu.aplhagramms.main.infastructure;

import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.services.FormsService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/form", produces = "application/json")
public class FormController {
    @Autowired
    private FormsService formsService;
/*String title, Long companyId, String shortDisc, String fullDisc,
                                 LocalDateTime createTime, LocalDateTime start, LocalDateTime end*/
    @PostMapping("/create")
    public ResponseEntity create(){
        try{
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body(formsService.create("bb", 1L,
                            "shortDisc", "fullDisc",
                            LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        }
        catch (Exception e){
            return ResponseEntity.status(200).body(e.getMessage());
        }
    }
}
