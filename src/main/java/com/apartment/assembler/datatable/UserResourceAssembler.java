package com.apartment.assembler.datatable;

import com.apartment.controller.api.v1.UserController;
import com.apartment.model.Role;
import com.apartment.model.User;
import com.apartment.resource.datatable.RoleResource;
import com.apartment.resource.datatable.UserResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResourceAssembler extends DataTableResourceAssembler<User, UserResource> {
    private final RoleResourceAssembler roleResourceAssembler;
    public UserResourceAssembler(RoleResourceAssembler roleResourceAssembler) {
        super(UserController.class, UserResource.class);
        this.roleResourceAssembler = roleResourceAssembler;
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = createResourceWithId(user.getId(), user);
        resource.setFirstName(user.getFirstName());
        resource.setLastName(user.getLastName());
        resource.setEmail(user.getEmail());
        resource.setUsername(user.getUsername());
        resource.setBaseId(user.getId());
        resource.setEnabled(user.isEnabled());
        List<RoleResource> roleResourceList = new ArrayList<>();

        for(Role role : user.getRoles()){
            roleResourceList.add(roleResourceAssembler.toResource(role));
        }
        resource.setResources(roleResourceList);
        return resource;
    }
}
