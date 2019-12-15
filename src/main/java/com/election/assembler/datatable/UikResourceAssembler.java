package com.election.assembler.datatable;

import com.election.controller.api.v1.UikController;
import com.election.model.Uik;
import com.election.resource.datatable.UikResource;
import org.springframework.stereotype.Component;

@Component
public class UikResourceAssembler extends DataTableResourceAssembler<Uik, UikResource> {
    public UikResourceAssembler() {
        super(UikController.class, UikResource.class);
    }

    @Override
    public UikResource toResource(Uik uik) {

        UikResource resource = createResourceWithId(uik.getId(), uik);

        resource.setAddress(uik.getAddress());
        resource.setLocationId(uik.getLocation().getId());
        resource.setLocationTitle(uik.getLocation().getTitle());
        resource.setTitle(uik.getTitle());
        resource.setBaseId(uik.getId());

        return resource;
    }
}
