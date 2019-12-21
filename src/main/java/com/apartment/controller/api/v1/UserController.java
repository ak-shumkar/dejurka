package com.apartment.controller.api.v1;

import com.apartment.assembler.datatable.RoleResourceAssembler;
import com.apartment.assembler.datatable.UserResourceAssembler;
import com.apartment.dto.ApiResponse;
import com.apartment.dto.UserDto;
import com.apartment.model.User;
import com.apartment.resource.datatable.UserResource;
import com.apartment.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserController {

    private final UserService userService;
    private final UserResourceAssembler assembler;


    @Autowired
    public UserController(UserService userService, UserResourceAssembler assembler, RoleResourceAssembler resourceAssembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") User user){

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<UserResource> list(
            @RequestParam(name = "role", required = false) Long roleId
    ) {
        return assembler.toResources(userService.getAllByRole(roleId));
    }



    @PostMapping ("edit/{id}")
    public ResponseEntity<?> edit(
            @RequestBody UserDto target, @PathVariable(name = "id") User src
    ){
        userService.edit(src, target);
        return new ResponseEntity<>(new ApiResponse(true, "UIK updated"), HttpStatus.OK);
    }



}
