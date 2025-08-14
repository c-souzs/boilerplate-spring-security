package com.souzs.boilerplate_spring_security.services;

import com.souzs.boilerplate_spring_security.domain.dtos.UserRequestDTO;
import com.souzs.boilerplate_spring_security.domain.dtos.UserResponseDTO;
import com.souzs.boilerplate_spring_security.domain.entities.Role;
import com.souzs.boilerplate_spring_security.domain.entities.User;
import com.souzs.boilerplate_spring_security.domain.enums.ERole;
import com.souzs.boilerplate_spring_security.repositories.UserRepository;
import com.souzs.boilerplate_spring_security.security.UserDetailsImpl;
import com.souzs.boilerplate_spring_security.security.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CookieService cookieService;

    @Transactional
    public void signup(HttpServletResponse response, UserRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getUsername());

        String password = passwordEncoder.encode(dto.getPassword());
        user.setPassword(password);

        Role roleBasic = new Role();
        roleBasic.setId(ERole.ROLE_USER.getId());
        roleBasic.setName(ERole.ROLE_USER.name());
        user.setRoles(Set.of(roleBasic));

        user = userRepository.save(user);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        Cookie cookie = generateTokenAndCookie(authentication);

        response.addCookie(cookie);
    }

    @Transactional
    public void signin(HttpServletResponse response, UserRequestDTO dto) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );

            Cookie cookie = generateTokenAndCookie(authentication);

            response.addCookie(cookie);
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos.");
        }
    }

    public void signout(HttpServletResponse response) {
        Cookie cookie = cookieService.removeTokenInCookie();

        response.addCookie(cookie);
    }

    public UserResponseDTO me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new UserResponseDTO(userDetails.getUser());
    }

    private Cookie generateTokenAndCookie(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return cookieService.addTokenInCookie(token);
    }
}
