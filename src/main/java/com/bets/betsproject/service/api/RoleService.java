package com.bets.betsproject.service.api;

import com.bets.betsproject.model.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role updateRole(Role role, Integer id);

    void deleteRole(Integer id);
}
