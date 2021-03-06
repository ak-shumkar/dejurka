package com.apartment.assembler.datatable;

import com.apartment.controller.api.v1.LocationController;
import com.apartment.model.Location;
import com.apartment.resource.datatable.LocationResource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LocationResourceAssembler extends DataTableResourceAssembler<Location, LocationResource> {
    public LocationResourceAssembler() {
        super(LocationController.class, LocationResource.class);
    }

    @Override
    public LocationResource toResource(Location location) {

        LocationResource resource = createResourceWithId(location.getId(), location);

        resource.setLocationLevel(location.getLocationLevel());
        resource.setLocationType(location.getLocationType());
        resource.setTitle(location.getTitle());
        if(Objects.nonNull(location.getParent())){
            resource.setParentId(location.getParent().getId());
            resource.setParentTitle(location.getParent().getTitle());
        }
        resource.setBaseId(location.getId());

        return resource;
    }
}
