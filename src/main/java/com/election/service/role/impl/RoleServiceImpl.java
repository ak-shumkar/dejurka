package com.election.service.role.impl;

import com.election.dto.LocationDto;
import com.election.dto.RoleDto;
import com.election.model.Location;
import com.election.model.Role;
import com.election.repository.RoleRepository;
import com.election.service.role.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role create(RoleDto roleDto){
        Role role = new Role();
        role.setCode(roleDto.getCode())
                .setTitle(roleDto.getTitle());

        return role;
    }

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByCode(String code) {
        return roleRepository.findByCode(code);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existByCode(String code) {
        return roleRepository.existsByCode(code);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void edit(Role target, RoleDto src){
        target.setTitle(src.getTitle())
                .setCode(src.getCode());
    }
}
