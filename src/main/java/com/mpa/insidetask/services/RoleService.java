package com.mpa.insidetask.services;

import com.mpa.insidetask.domain.models.Role;
import com.mpa.insidetask.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
    }

    public Role findRole(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
