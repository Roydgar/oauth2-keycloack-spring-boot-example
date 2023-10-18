package com.example.oauth2keycloackspringbootexample.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SecuredController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }
}
