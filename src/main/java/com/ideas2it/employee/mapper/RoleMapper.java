package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.RoleDto;
import com.ideas2it.employee.model.Role;

public class RoleMapper {
    public static RoleDto convertRoleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        if(role != null) {
            if(role.getRoleId() != 0) {
                roleDto.setRoleId(role.getRoleId());
            }
            roleDto.setRole(role.getRole());
        }
        return roleDto;
    }

    public static Role convertRoleDtoToRole(RoleDto roleDto) {
        Role role = new Role();
        if(roleDto != null) {
            if(roleDto.getRoleId() != 0) {
                role.setRoleId(roleDto.getRoleId());
            }
            role.setRole(roleDto.getRole());
        }
        return role;
    }

}
