package com.souzs.boilerplate_spring_security.controllers;

import com.souzs.boilerplate_spring_security.domain.dtos.UserRequestDTO;
import com.souzs.boilerplate_spring_security.domain.dtos.UserResponseDTO;
import com.souzs.boilerplate_spring_security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin() {
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO dto) {
        UserResponseDTO result = authService.signup(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
