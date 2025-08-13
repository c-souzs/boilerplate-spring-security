package com.souzs.boilerplate_spring_security.controllers;

import com.souzs.boilerplate_spring_security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class AuthController {
    @Autowired
    private AuthService authService;
}
