package com.apartment.assembler.datatable;

import com.apartment.controller.api.v1.HouseController;
import com.apartment.model.House;
import com.apartment.resource.datatable.HouseResource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class HouseResourceAssembler extends DataTableResourceAssembler<House, HouseResource>{
    private final LocationResourceAssembler locationResourceAssembler;
    private final SeriesResourceAssembler seriesResourceAssembler;
    public HouseResourceAssembler(LocationResourceAssembler locationResourceAssembler, SeriesResourceAssembler seriesResourceAssembler) {
        super(HouseController.class, HouseResource.class);
        this.locationResourceAssembler = locationResourceAssembler;
        this.seriesResourceAssembler = seriesResourceAssembler;
    }

    @Override
    public HouseResource toResource(House entity) {
        HouseResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource,"location");
        resource.setLocationResource(locationResourceAssembler.toResource(entity.getLocation()));
        resource.setSeriesResource(seriesResourceAssembler.toResource(entity.getSeries()));
        return resource;
    }
}
