package com.souzs.boilerplate_spring_security.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtService {
    // Gera o token a partir do usuario
    String generateToken(Authentication authentication);

    // Verifica se o token esta valido
    boolean isTokenValid(String token);

    // Extrai o token a partir dos cookies
    String extractTokenFromCookie(HttpServletRequest request);

    // Extrai o email do token
    String extractClaimFromToken(String token, String claimName);
}
