package com.example.main.service.interfaces;

import com.example.main.exeption.RoleNotFoundException;
import com.example.main.model.Roles;

import java.util.List;

public interface RoleService {
    Roles createRole(Roles role) throws Exception;
    List<Roles> getAllRoles();

    boolean deleteRole(Long id) throws RoleNotFoundException;
    Roles getRoleById(Long id) throws RoleNotFoundException;
    Roles updateRole(Long id, Roles role) throws RoleNotFoundException;
}
