package com.election.controller.api.v1;

import com.election.assembler.datatable.VoterResourceAssembler;
import com.election.dto.ApiResponse;
import com.election.dto.LocationDto;
import com.election.dto.VoterDto;
import com.election.model.Location;
import com.election.model.Voter;
import com.election.resource.datatable.VoterResource;
import com.election.service.voter.VoterService;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(value = "/api/v1/voter/")
public class VoterController {

    private final VoterService voterService;
    private final VoterResourceAssembler assembler;

    @PostMapping ("create")
    public ResponseEntity<?> create(@RequestBody VoterDto voterDto){

        voterService.create(voterDto);
        return new ResponseEntity<>(new ApiResponse(true, "New Voter added"), HttpStatus.OK);
    }

    @PostMapping ("edit/{id}")
    public ResponseEntity<?> edit(@RequestBody VoterDto target, @PathVariable(name = "id") Voter src){
        voterService.edit(src, target);
        return new ResponseEntity<>(new ApiResponse(true, "Voter updated"), HttpStatus.OK);
    }


    @GetMapping (value = "{id}")
    public VoterResource findById(@PathVariable (name = "id") Long id){

        Voter voter = voterService.findById(id);
        return assembler.toResource(voter);
    }

    @GetMapping ("list")
    public PagedResources<VoterResource> list(
            PagedResourcesAssembler<Voter> pagedAssembler,
            @RequestParam(name = "uik", required = false) Long uik,
            Pageable pageable

    ){
        return pagedAssembler.toResource(
                voterService.listAllPageable(uik, pageable),
                new VoterResourceAssembler()
        );
    }

    @GetMapping ("list-by-role")
    public PagedResources<VoterResource> listByRole(
            PagedResourcesAssembler<Voter> pagedAssembler,
            @RequestParam(name = "role", required = false) Long role,
            Pageable pageable

    ){
        return pagedAssembler.toResource(
                voterService.listAllByRolePageable(role, pageable),
                new VoterResourceAssembler()
        );
    }

    @GetMapping ("list-by-filter")
    public List<VoterResource> listByFilter(
            @RequestParam(name = "location") Long locationId,
            @RequestParam(name = "month") Integer month

    ){
        return assembler.toResources(voterService.listByFilter(locationId, month));
    }



}
