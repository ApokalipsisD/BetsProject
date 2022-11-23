package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Role;
import com.bets.betsproject.repository.RoleRepository;
import com.bets.betsproject.service.api.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role", "Id", id)
        );
    }

    @Override
    public Role updateRole(Role role, Integer id) {
        Role newRole = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
        newRole.setName(role.getName());
        roleRepository.save(newRole);
        return newRole;
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
        roleRepository.deleteById(id);
    }
}
