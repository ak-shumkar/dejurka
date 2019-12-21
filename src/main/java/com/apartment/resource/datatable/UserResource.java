package com.apartment.resource.datatable;

import com.apartment.resource.datatable.base.BaseResource;
import lombok.Data;

import java.util.List;

@Data
public class UserResource extends BaseResource {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Boolean enabled;
    private List<RoleResource> resources;
}
