package com.apartment.service.role;

import com.apartment.dto.RoleDto;
import com.apartment.model.Role;

import java.util.List;

public interface RoleService {

    Role create(RoleDto roleDto);

    List<Role> getAll();

    Role findByCode(String code);

    Role findById(Long id);

    boolean existByCode(String code);

    void delete(Long id);

    void edit(Role src, RoleDto target);
}
