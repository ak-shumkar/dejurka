package com.apartment.controller.api.v1;


import com.apartment.assembler.datatable.HouseResourceAssembler;
import com.apartment.assembler.datatable.LocationResourceAssembler;
import com.apartment.assembler.datatable.SeriesResourceAssembler;
import com.apartment.dto.ApiResponse;
import com.apartment.dto.HouseDto;
import com.apartment.model.House;
import com.apartment.resource.datatable.HouseResource;
import com.apartment.service.house.HouseService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/house")
public class HouseController {

    private final HouseService houseService;
    private final HouseResourceAssembler assembler;
    private final LocationResourceAssembler locationResourceAssembler;
    private final SeriesResourceAssembler seriesResourceAssembler;


    public HouseController(HouseService houseService, HouseResourceAssembler assembler, LocationResourceAssembler locationResourceAssembler, SeriesResourceAssembler seriesResourceAssembler) {
        this.houseService = houseService;
        this.assembler = assembler;
        this.locationResourceAssembler = locationResourceAssembler;
        this.seriesResourceAssembler = seriesResourceAssembler;
    }


    @PostMapping ("/create")
    public ResponseEntity<?> create(
            @RequestPart(name = "file") MultipartFile[] files,
            @ModelAttribute HouseDto houseDto
    ) throws Exception {

        houseService.create(houseDto,files);
        return new ResponseEntity<>(new ApiResponse(true, "New House added"), HttpStatus.OK);
    }

    @PostMapping ("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody HouseDto houseDto, @PathVariable (name = "id") House house){
        houseService.update(houseDto, house);
        return new ResponseEntity<>(new ApiResponse(true, "House updated"), HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")
    public HouseResource findById(@PathVariable (name = "id") Long id){

        return assembler.toResource(houseService.findById(id));
    }

    @GetMapping("/list")
    public List<HouseResource> list() {

        return assembler.toResources(houseService.findAll().stream().filter(e -> e.getHidden() == null || !e.getHidden()).collect(Collectors.toList()));
    }

    @PostMapping("/filter")
    public PagedResources<HouseResource> filter(PagedResourcesAssembler<House> pagedAssembler, @RequestBody HouseDto houseDto, Pageable pageable) {

        return pagedAssembler.toResource(houseService.filter(houseDto,pageable),new HouseResourceAssembler(locationResourceAssembler, seriesResourceAssembler));
    }

    @DeleteMapping
    public Boolean delete(
            @RequestParam("id") House house
    ) {
        house.setHidden(true);
        houseService.save(house);
        return true;
    }
}
