package com.souzs.boilerplate_spring_security.services;

import com.souzs.boilerplate_spring_security.domain.dtos.UserRequestDTO;
import com.souzs.boilerplate_spring_security.domain.dtos.UserResponseDTO;
import com.souzs.boilerplate_spring_security.domain.entities.Role;
import com.souzs.boilerplate_spring_security.domain.entities.User;
import com.souzs.boilerplate_spring_security.domain.enums.ERole;
import com.souzs.boilerplate_spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO signup(UserRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getUsername());

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        Role roleBasic = new Role();
        roleBasic.setId(ERole.ROLE_USER.getId());
        user.setRoles(Set.of(roleBasic));

        user = userRepository.save(user);

        return new UserResponseDTO(user);
    }
}
