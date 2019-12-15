package com.election.controller.api.v1;

import com.election.assembler.datatable.UikResourceAssembler;
import com.election.assembler.datatable.UserResourceAssembler;
import com.election.assembler.datatable.VoterResourceAssembler;
import com.election.dto.ApiResponse;
import com.election.dto.UserDto;
import com.election.model.User;
import com.election.resource.datatable.UikResource;
import com.election.resource.datatable.UserResource;
import com.election.resource.datatable.VoterResource;
import com.election.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserController {

    private final UserService userService;
    private final VoterResourceAssembler voterResourceAssembler;
    private final UikResourceAssembler uikResourceAssembler;
    private final UserResourceAssembler assembler;

    @Autowired
    public UserController(UserService userService, VoterResourceAssembler voterResourceAssembler, UikResourceAssembler uikResourceAssembler, UserResourceAssembler assembler) {
        this.userService = userService;
        this.voterResourceAssembler = voterResourceAssembler;
        this.uikResourceAssembler = uikResourceAssembler;
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

    @GetMapping ("voter-list/{agitator-id}")
    public List<VoterResource> voterList(
            @PathVariable(name = "agitator-id") Long agitatorId
    ){
        return voterResourceAssembler.toResources(userService.userVoterList(agitatorId));
    }

    @PostMapping ("edit/{id}")
    public ResponseEntity<?> edit(
            @RequestBody UserDto target, @PathVariable(name = "id") User src
    ){
        userService.edit(src, target);
        return new ResponseEntity<>(new ApiResponse(true, "UIK updated"), HttpStatus.OK);
    }

    @GetMapping ("uik-list/{agitator-id}")
    public List<UikResource> uikList(
            @PathVariable(name = "agitator-id") Long userId
    ){
        return uikResourceAssembler.toResources(userService.userUikList(userId));
    }

}
