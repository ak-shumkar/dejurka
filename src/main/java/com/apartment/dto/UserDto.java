package com.apartment.dto;

import com.apartment.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {


    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean enabled;
    private List<Long> roles;


    public User toUser(UserDto userDto){

        User user = new User();
        user.setId(userDto.id);
        user.setUsername(userDto.username);
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);
        user.setEmail(userDto.email);

        return user;
    }

    public static UserDto fromUser(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles().stream().map(r->{return r.getId();}).collect(Collectors.toList()));
        userDto.setEnabled(user.isEnabled());

        return userDto;
    }
}
