package com.election.repository;

import com.election.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);

    Boolean existsByCode(String code);
}
