package com.apartment.repository;

import com.apartment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);

    Boolean existsByCode(String code);
}
