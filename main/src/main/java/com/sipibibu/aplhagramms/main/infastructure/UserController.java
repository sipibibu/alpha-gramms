/*
package com.sipibibu.aplhagramms.main.infastructure;

import com.sipibibu.aplhagramms.main.app.entities.UserEntity;
import com.sipibibu.aplhagramms.main.app.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@RestController("/users/**")
public class UserController {

    @Autowired UserService userService;
    @RequestMapping(path = "/getUser/{id}", method= RequestMethod.GET)
    public Optional<UserEntity> getUser(@PathVariable("id") String id) {
        var user = userService.getById(id);
        return user;
    }
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
*/
