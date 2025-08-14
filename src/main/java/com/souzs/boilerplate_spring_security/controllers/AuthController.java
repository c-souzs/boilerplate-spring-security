package com.souzs.boilerplate_spring_security.controllers;

import com.souzs.boilerplate_spring_security.domain.dtos.UserRequestDTO;
import com.souzs.boilerplate_spring_security.domain.dtos.UserResponseDTO;
import com.souzs.boilerplate_spring_security.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDTO dto, HttpServletResponse response) {
        authService.signin(response, dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO dto, HttpServletResponse response) {
        authService.signup(response, dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signout")
    public ResponseEntity<UserResponseDTO> signout(HttpServletResponse response) {
        authService.signout(response);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me() {
        UserResponseDTO dto = authService.me();

        return ResponseEntity.ok().body(dto);
    }
}
