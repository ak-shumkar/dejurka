package com.apartment.security;

import com.apartment.model.Role;
import com.apartment.model.User;
import com.apartment.security.jwt.JwtUser;
import com.apartment.security.jwt.JwtUserFactory;
import com.apartment.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + s + " not found");
        }

        // Set User Security
        user
                .setsRoles(
                        user.getRoles()
                )
                .setsAuthorities(
                             user.getRoles()
                                                .stream()
                                                .map(Role::getCode)
                                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                );

        JwtUser jwtUser = JwtUserFactory.create(user);

        LOGGER.info("IN loadUserByUsername - user with username: {} successfully loaded", s);

        return jwtUser;
    }
}
