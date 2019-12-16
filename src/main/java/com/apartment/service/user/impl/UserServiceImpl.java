package com.apartment.service.user.impl;

import com.apartment.dto.UserDto;
import com.apartment.model.*;
import com.apartment.repository.LocationRepository;
import com.apartment.repository.RoleRepository;
import com.apartment.repository.UserRepository;
import com.apartment.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LocationRepository locationRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            LocationRepository locationRepository,
            BCryptPasswordEncoder passwordEncoder
      ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.locationRepository = locationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User registeredUser = userRepository.save(user);

        LOGGER.info("IN register - user: {} successfully registered", registeredUser.getUsername());

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void edit(User src, UserDto target) {

        Set<Role> roles = new HashSet<>();
        List<Location> locations = new ArrayList<>();
        // ROLES
        for(Long roleId: target.getRoles()){
            roles.add(roleRepository.findById(roleId).orElse(null));
        }
        if(roles.size() > 0)
            src.setRoles(roles);

        // LOCATIONS
        for(Long locationId: target.getLocations()){
            locations.add(locationRepository.findById(locationId).orElse(null));
        }
        if(locations.size()>0)
            src.setLocations(locations);

        //CHECK FOR PASSWORD
        if(!StringUtils.isEmpty(target.getPassword())){
            src.setPassword(passwordEncoder.encode(target.getPassword()));
        }

        userRepository.save(src
                .setFirstName(target.getFirstName())
                .setLastName(target.getLastName())
                .setEmail(target.getEmail())
                .setEnabled(target.getEnabled())
               );
    }


    @Override
    public List<User> getAllByRole(Long roleId) {

        if(Objects.nonNull(roleId)){
            Role role = roleRepository.findById(roleId).orElse(null);
            return userRepository.findAllByRolesContains(role);
        } else {
            return userRepository.findAll();
        }
    }

}
