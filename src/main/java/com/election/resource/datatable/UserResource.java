package com.election.resource.datatable;

import com.election.resource.datatable.base.BaseResource;
import lombok.Data;

import java.util.List;

@Data
public class UserResource extends BaseResource {

    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private List<String> locations;
}
