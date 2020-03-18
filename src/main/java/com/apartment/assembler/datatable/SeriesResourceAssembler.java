package com.apartment.assembler.datatable;

import com.apartment.controller.api.v1.LocationController;
import com.apartment.model.Location;
import com.apartment.model.Series;
import com.apartment.resource.datatable.LocationResource;
import com.apartment.resource.datatable.SeriesResource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SeriesResourceAssembler extends DataTableResourceAssembler<Series, SeriesResource> {
    public SeriesResourceAssembler() {
        super(LocationController.class, SeriesResource.class);
    }

    @Override
    public SeriesResource toResource(Series series) {

        if (series == null){
            return null;
        }
        SeriesResource resource = createResourceWithId(series.getId(), series);

        resource.setName(series.getName());
        resource.setBaseId(series.getId());

        return resource;
    }
}
