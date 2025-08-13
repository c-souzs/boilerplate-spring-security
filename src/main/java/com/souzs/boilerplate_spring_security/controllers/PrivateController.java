package com.souzs.boilerplate_spring_security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    public ResponseEntity<String> publicMessage() {
        return ResponseEntity.ok("This resource is private! \uD83D\uDD12");
    }
}
