package com.apartment.assembler.datatable;

import com.apartment.controller.api.v1.UserController;
import com.apartment.model.User;
import com.apartment.resource.datatable.UserResource;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserResourceAssembler extends DataTableResourceAssembler<User, UserResource> {
    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = createResourceWithId(user.getId(), user);
        resource.setFirstName(user.getFirstName());
        resource.setLastName(user.getLastName());
        resource.setEmail(user.getEmail());
        resource.setLocations(user.getLocations().stream().map(l->{return l.getTitle();}).collect(Collectors.toList()));
        resource.setBaseId(user.getId());
        resource.setEnabled(user.isEnabled());
        return resource;
    }
}
