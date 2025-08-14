package com.souzs.boilerplate_spring_security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping
    public ResponseEntity<String> publicMessage() {
        System.out.println("Chamou aqui");
        return ResponseEntity.ok("This resource is public! \uD83D\uDD13");
    }
}
