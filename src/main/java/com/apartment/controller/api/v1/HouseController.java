package com.apartment.controller.api.v1;


import com.apartment.assembler.datatable.HouseResourceAssembler;
import com.apartment.dto.ApiResponse;
import com.apartment.dto.HouseDto;
import com.apartment.dto.LocationDto;
import com.apartment.model.House;
import com.apartment.model.Location;
import com.apartment.resource.datatable.HouseResource;
import com.apartment.resource.datatable.LocationResource;
import com.apartment.service.house.HouseService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/house/")
public class HouseController {

    private final HouseService houseService;
    private final HouseResourceAssembler assembler;

    public HouseController(HouseService houseService, HouseResourceAssembler assembler) {
        this.houseService = houseService;
        this.assembler = assembler;
    }


    @PostMapping ("create")
    public ResponseEntity<?> create(
            @RequestPart(name = "file") MultipartFile[] files,
            @ModelAttribute HouseDto houseDto
    ) throws Exception {

        houseService.create(houseDto,files);
        return new ResponseEntity<>(new ApiResponse(true, "New House added"), HttpStatus.OK);
    }

    @PostMapping ("edit/{id}")
    public ResponseEntity<?> update(@RequestBody HouseDto houseDto, @PathVariable (name = "id") House house){
        houseService.update(houseDto, house);
        return new ResponseEntity<>(new ApiResponse(true, "House updated"), HttpStatus.OK);
    }

    @GetMapping (value = "{id}")
    public HouseResource findById(@PathVariable (name = "id") Long id){

        return assembler.toResource(houseService.findById(id));
    }

    @GetMapping("/list")
    public List<HouseResource> list(Pageable pageable) {

        return assembler.toResources(houseService.findAll(pageable));
    }
}
