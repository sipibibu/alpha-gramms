package com.nthokar.spring2023.userauth;

import com.sun.net.httpserver.HttpServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.security.Principal;

@RestController
public class MyController {

    @GetMapping("/admin")
    public String homeAdmin(Principal principal) {
        return "Hello mr. " + principal.getName();
    }

    @GetMapping("/user")
    public String homeUser(Principal principal) {
        return "Hello mr. " + principal.getName();
    }
}