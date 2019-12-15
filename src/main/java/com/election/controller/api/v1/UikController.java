package com.election.controller.api.v1;

import com.election.assembler.datatable.UikResourceAssembler;
import com.election.dto.ApiResponse;
import com.election.dto.UikDto;
import com.election.model.Uik;
import com.election.resource.datatable.UikResource;
import com.election.service.uik.UikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/uik/")
public class UikController {
    
    private final UikService uikService;
    private final UikResourceAssembler assembler;

    public UikController(UikService uikService, UikResourceAssembler assembler) {
        this.uikService = uikService;
        this.assembler = assembler;
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody UikDto UikDto){

        uikService.create(UikDto);
        return new ResponseEntity<>(new ApiResponse(true, "New UIK added"), HttpStatus.OK);
    }

    @PostMapping ("edit/{id}")
    public ResponseEntity<?> edit(@RequestBody UikDto target, @PathVariable(name = "id") Uik src){
        uikService.edit(src, target);
        return new ResponseEntity<>(new ApiResponse(true, "UIK updated"), HttpStatus.OK);
    }

    @GetMapping (value = "{id}")
    public UikResource findById(@PathVariable (name = "id") Long id){
        return assembler.toResource(uikService.findById(id));
    }

    @GetMapping("/list")
    public List<UikResource> list(
            @RequestParam(name = "location", required = false) Long location
    ) {

        return assembler.toResources(uikService.list(location));
    }
}
