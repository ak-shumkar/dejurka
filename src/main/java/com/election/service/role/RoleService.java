package com.election.service.role;

import com.election.dto.LocationDto;
import com.election.dto.RoleDto;
import com.election.model.Location;
import com.election.model.Role;

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
