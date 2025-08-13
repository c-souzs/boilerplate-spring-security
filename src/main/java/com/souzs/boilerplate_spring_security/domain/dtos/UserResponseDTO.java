package com.souzs.boilerplate_spring_security.domain.dtos;

import com.souzs.boilerplate_spring_security.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private long id;
    private String username;
    private List<RoleDTO> roles;

    public UserResponseDTO(User user) {
        id = user.getId();
        username = user.getEmail();
        roles = user.getRoles().stream().map(role -> new RoleDTO(role)).toList();
    }
}
