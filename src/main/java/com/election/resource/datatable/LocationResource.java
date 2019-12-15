package com.election.resource.datatable;

import com.election.resource.datatable.base.BaseResource;
import lombok.Data;

@Data
public class LocationResource extends BaseResource {

    private String parentTitle;
    private Long parentId;
    private Integer locationType;
    private Integer locationLevel;
    private String title;

}
