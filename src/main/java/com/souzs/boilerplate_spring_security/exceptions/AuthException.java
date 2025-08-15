package com.souzs.boilerplate_spring_security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String message) {
        super(message);
    }
}
