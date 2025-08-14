package com.souzs.boilerplate_spring_security.services;

import com.souzs.boilerplate_spring_security.security.jwt.JwtServiceImpl;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Value("${token.jwt.expirationTime}")
    private int expirationTime;

    public Cookie addCookie(String key, String value) {
        return new Cookie(key, value);
    }

    public Cookie addTokenInCookie(String token) {
        Cookie cookie = addCookie(JwtServiceImpl.NAME_COOKIE_TOKEN, token);

        settingsHttpOnlyToken(cookie, expirationTime * 60 * 60);

        return cookie;
    }

    public Cookie removeTokenInCookie() {
        Cookie cookie = addCookie(JwtServiceImpl.NAME_COOKIE_TOKEN, null);

        settingsHttpOnlyToken(cookie, 0);

        return cookie;
    }

    private void settingsHttpOnlyToken(Cookie cookie, int maxAge) {
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
    }
}
