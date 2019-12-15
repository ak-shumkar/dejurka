package com.election.controller.api.v1;


import com.election.assembler.datatable.LocationResourceAssembler;
import com.election.dto.ApiResponse;
import com.election.dto.LocationDto;
import com.election.model.Location;
import com.election.resource.datatable.LocationResource;
import com.election.service.location.LocationService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/location/")
public class LocationController {

    private final LocationService locationService;
    private final LocationResourceAssembler assembler;

    public LocationController(LocationService locationService, LocationResourceAssembler assembler) {
        this.locationService = locationService;
        this.assembler = assembler;
    }

    @PostMapping ("create")
    public ResponseEntity<?> create(@RequestBody LocationDto locationDto){

        locationService.create(locationDto);
        return new ResponseEntity<>(new ApiResponse(true, "New Location added"), HttpStatus.OK);
    }

    @PostMapping ("edit/{id}")
    public ResponseEntity<?> edit(@RequestBody LocationDto target, @PathVariable (name = "id") Location src){
        locationService.edit(src, target);
        return new ResponseEntity<>(new ApiResponse(true, "Location updated"), HttpStatus.OK);
    }

    @GetMapping (value = "{id}")
    public LocationResource findById(@PathVariable (name = "id") Long id){

        return assembler.toResource(locationService.findById(id));
    }

    @GetMapping("/list")
    public List<LocationResource> list(
            @RequestParam(name = "level", required = false) Integer level,
            @RequestParam(name = "parent", required = false) Long parent,
            @RequestParam(name = "type", required = false) Integer type,
            @QuerydslPredicate(root = Location.class) Predicate predicate,
            Authentication authentication) {

        return assembler.toResources(locationService.list(level, type, parent));
    }
}
