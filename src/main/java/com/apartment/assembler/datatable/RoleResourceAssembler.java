package com.apartment.assembler.datatable;

import com.apartment.controller.api.v1.RoleController;
import com.apartment.model.Role;
import com.apartment.resource.datatable.RoleResource;
import org.springframework.stereotype.Component;

@Component
public class RoleResourceAssembler extends DataTableResourceAssembler<Role, RoleResource> {
    public RoleResourceAssembler() {
        super(RoleController.class, RoleResource.class);
    }

    @Override
    public RoleResource toResource(Role role) {
        RoleResource resource = createResourceWithId(role.getId(), role);
        resource.setRoleTitle(role.getTitle());
        resource.setRoleCode(role.getCode());
        resource.setBaseId(role.getId());
        return resource;
    }
}
