package com.election.security.jwt;

import com.election.model.Role;
import com.election.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 */
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {

        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.isEnabled(),
                user.getUpdatedAt(),
                mapToGrantedAuthorities(user)

        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(User user) {

        return Stream
                .concat(
                        user.getsRoles()
                                .stream()
                                .map(Role::getCode)
                                .map(SimpleGrantedAuthority::new),
                        user.getsPermissions()
                                .stream()
                                .map(Permission::getName)
                                .map(SimpleGrantedAuthority::new)
                )
                .collect(Collectors.toList());
    }
}
