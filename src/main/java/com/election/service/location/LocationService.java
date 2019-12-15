package com.election.service.location;

import com.election.dto.LocationDto;
import com.election.model.Location;
import com.querydsl.core.types.Predicate;

public interface LocationService {

    Location create(LocationDto locationDto);
    void edit(Location src, LocationDto target);
    Location findById(Long id);
    Iterable<Location> list(Integer level, Integer type, Long parent);
}
