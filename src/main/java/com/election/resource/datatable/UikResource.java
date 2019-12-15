package com.election.resource.datatable;

import com.election.resource.datatable.base.BaseResource;
import lombok.Data;

@Data
public class UikResource extends BaseResource {

    private String title;
    private String address;
    private Long locationId;
    private String locationTitle;
}
