package com.souzs.boilerplate_spring_security.domain.dtos;

import com.souzs.boilerplate_spring_security.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private long id;
    private String name;

    public RoleDTO(Role role) {
        id = role.getId();
        name = role.getName();
    }
}
