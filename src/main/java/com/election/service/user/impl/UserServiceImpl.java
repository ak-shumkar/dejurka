package com.election.service.user.impl;

import com.election.dto.UserDto;
import com.election.model.*;
import com.election.repository.LocationRepository;
import com.election.repository.RoleRepository;
import com.election.repository.UserRepository;
import com.election.service.uik.UikService;
import com.election.service.user.UserService;
import com.election.service.voter.VoterService;
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
    private final UikService uikService;
    private final VoterService voterService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            LocationRepository locationRepository,
            BCryptPasswordEncoder passwordEncoder,
            UikService uikService,
            VoterService voterService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.locationRepository = locationRepository;
        this.passwordEncoder = passwordEncoder;
        this.uikService = uikService;
        this.voterService = voterService;
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
    public List<Uik> userUikList(Long userId) {
        return uikService.listByUser(userId);
    }

    @Override
    public List<Voter> userVoterList(Long userId) {
        return voterService.listByUser(userId);
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
