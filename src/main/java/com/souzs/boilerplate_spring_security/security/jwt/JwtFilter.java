package com.souzs.boilerplate_spring_security.security.jwt;

import com.souzs.boilerplate_spring_security.exceptions.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final List<String> ENDPOINTS_AUTH_PUBLIC = List.of(
            "/auth/signin",
            "/auth/signup"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtService.extractTokenFromCookie(request);
        boolean validateToken = jwtService.isTokenValid(token);

        if (checkLoggedUser(request.getRequestURI(), token)) throw new AuthException("Usuário já está logado");

        try {

            if(token != null && validateToken) {
                String email = jwtService.extractClaimFromToken(token, "sub");

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception  e) {
            throw new AuthException("Erro de autenticação");
        }

        filterChain.doFilter(request, response);
    }

    private boolean checkLoggedUser(String path, String token) {
        boolean isAnonymousOnly = ENDPOINTS_AUTH_PUBLIC.contains(path);

        return isAnonymousOnly && token != null;
    }
}
