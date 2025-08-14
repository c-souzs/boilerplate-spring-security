package com.souzs.boilerplate_spring_security.security.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.souzs.boilerplate_spring_security.domain.entities.User;
import com.souzs.boilerplate_spring_security.security.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${token.jwt.secret}")
    private String secretKey;

    @Value("${token.jwt.expirationTime}")
    private int expirationTime;

    public static String NAME_COOKIE_TOKEN = "token";

    @Override
    public String generateToken(Authentication authentication) {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();

            long userID = user.getId();
            String username = user.getEmail();

            List<String> roles = user.getRoles().stream()
                    .map(role -> role.getName())
                    .toList();

            Date now = new Date();
            long convertHoursToMs = (long) expirationTime * 60 * 60 * 1000;
            Date exp = new Date(now.getTime() + convertHoursToMs);

            JWSHeader header =  new JWSHeader(JWSAlgorithm.HS256);
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer(applicationName)
                    .issueTime(now)
                    .expirationTime(exp)
                    .claim("id", userID)
                    .claim("roles", roles)
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claims);
            signedJWT.sign(new MACSigner(secretKey));

            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new JwtException("Não foi possível gerar o token do usuário");
        }

    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            if(token == null) return false;

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secretKey);

            if(!signedJWT.verify(verifier)) return false;

            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return new Date().before(expiration);
        } catch (ParseException | JOSEException e) {
            return false;
        }
    }

    @Override
    public String extractTokenFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, NAME_COOKIE_TOKEN);
        if(cookie != null) return cookie.getValue();

        return null;
    }

    @Override
    public String extractClaimFromToken(String token, String claimName) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            var claimValue = claims.getClaim(claimName);

            return claimName != null ? claimValue.toString() : null;
        } catch (ParseException e) {
            return null;
        }
    }
}
