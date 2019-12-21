package com.apartment.controller.api.v1;

import com.apartment.dto.ApiResponse;
import com.apartment.dto.AuthenticationRequestDto;
import com.apartment.dto.RoleDto;
import com.apartment.dto.UserRegisterDto;
import com.apartment.model.Role;
import com.apartment.model.User;
import com.apartment.security.jwt.JwtTokenProvider;
import com.apartment.service.role.RoleService;
import com.apartment.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 */

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationRestController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserService userService,
            RoleService roleService,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();

            List<RoleDto> roleDtos = new ArrayList<>();
            Set<Role> userRoles = user.getRoles();

            for (Role role : userRoles){
                RoleDto roleDto = new RoleDto();

                roleDto.setCode(role.getCode());
                roleDto.setTitle(role.getTitle());
                roleDto.setId(role.getId());

                roleDtos.add(roleDto);
            }

            response.put("username", username);
            response.put("roles", roleDtos);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity<?>  register(@RequestBody UserRegisterDto userRegisterDto) {


        if(userService.existByUsername(userRegisterDto.getUsername())) {

            return new ResponseEntity<>(new ApiResponse(false,"Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if(userService.existByEmail(userRegisterDto.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(userRegisterDto.getUsername(), userRegisterDto.getName(), userRegisterDto.getSurname(), userRegisterDto.getEmail());

        user.setPassword(userRegisterDto.getPassword());
        user.setEnabled(true);

        Set<Role> roles = new HashSet<>();
        for(Long roleId: userRegisterDto.getRoles()){
            roles.add(roleService.findById(roleId));
        }

        user.setRoles(roles);

        User result = userService.register(user);

        /*URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdStudent.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(result);*/

        return new ResponseEntity<>(new ApiResponse(true, "User registered successfully"), HttpStatus.OK);
    }
}
