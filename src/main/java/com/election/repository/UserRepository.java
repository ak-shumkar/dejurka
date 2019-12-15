package com.election.repository;

import com.election.model.Role;
import com.election.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    Boolean  existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findAllByRolesContains(Role role);
}
