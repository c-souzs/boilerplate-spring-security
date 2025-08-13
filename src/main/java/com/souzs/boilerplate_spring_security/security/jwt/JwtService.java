package com.souzs.boilerplate_spring_security.security.jwt;

import com.nimbusds.jose.JOSEException;
import org.springframework.security.core.Authentication;

import java.text.ParseException;

public interface JwtService {
    // Gera o token a partir do usuario
    String generateToken(Authentication authentication);

    // Verifica se o token esta valido
    boolean isTokenValid(String token);
}
