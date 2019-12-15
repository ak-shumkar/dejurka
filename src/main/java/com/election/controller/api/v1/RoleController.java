package com.election.controller.api.v1;

import com.election.assembler.datatable.RoleResourceAssembler;
import com.election.dto.ApiResponse;
import com.election.dto.LocationDto;
import com.election.dto.RoleDto;
import com.election.model.Location;
import com.election.model.Role;
import com.election.resource.datatable.RoleResource;
import com.election.service.role.RoleService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(value = "/api/v1/role/")
public class RoleController {

    private final RoleService roleService;
    private final RoleResourceAssembler assembler;


    @PostMapping ("create")
    public ResponseEntity<?> create(@RequestBody RoleDto roleDto){

        roleService.create(roleDto);
        return new ResponseEntity<>(new ApiResponse(true, "New Role added"), HttpStatus.OK);
    }

    @GetMapping ("list")
    public List<RoleResource> list(){
        return assembler.toResources(roleService.getAll());
    }

    @PostMapping ("edit/{id}")
    public ResponseEntity<?> edit(@RequestBody RoleDto target, @PathVariable(name = "id") Role src){
        roleService.edit(src, target);
        return new ResponseEntity<>(new ApiResponse(true, "Role updated"), HttpStatus.OK);
    }

    @GetMapping (value = "{id}")
    public Role findById(@PathVariable(name = "id") Role role){

        return role;
    }


}
