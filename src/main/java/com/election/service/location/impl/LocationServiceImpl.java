package com.election.service.location.impl;

import com.election.dto.LocationDto;
import com.election.model.Location;
import com.election.model.QLocation;
import com.election.repository.LocationRepository;
import com.election.service.location.LocationService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Override
    public Location create(LocationDto locationDto) {
        Location location = new Location()
                .setLocationLevel(locationDto.getLocationLevel())
                .setLocationType(locationDto.getLocationType())
                .setTitle(locationDto.getTitle());

        if (locationDto.getParentId() != null) {
            location.setParent(locationRepository.getOne(locationDto.getParentId()));
        }

        return locationRepository.save(location);
    }


    @Override
    public void edit(Location src, LocationDto target) {
        src
                .setTitle(target.getTitle())
                .setLocationType(target.getLocationType())
                .setLocationLevel(target.getLocationLevel());

        if (target.getParentId() != null)
            src.setParent(locationRepository.getOne(target.getParentId()));

        locationRepository.save(src);
    }

    @Override
    public Location findById(Long id) {
        //TODO
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Location> list(Integer level, Integer type, Long parent) {

        final QLocation root = QLocation.location;
        final BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(level))
            builder.and(root.locationLevel.eq(level));
        if (Objects.nonNull(type))
            builder.and(root.locationType.eq(type));
        if (Objects.nonNull(parent))
            builder.and(root.parent.id.eq(parent));

        if(Objects.nonNull(builder.getValue()))
            return this.locationRepository.findAll(Objects.requireNonNull(builder.getValue()));
        else
            return this.locationRepository.findAll();
    }
}
