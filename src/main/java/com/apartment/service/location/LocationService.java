package com.apartment.service.location;

import com.apartment.dto.LocationDto;
import com.apartment.model.Location;

public interface LocationService {

    Location create(LocationDto locationDto);
    void edit(Location src, LocationDto target);
    Location findById(Long id);
    Iterable<Location> list(Integer level, Integer type, Long parent);
}
