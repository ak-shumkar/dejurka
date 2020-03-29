package com.apartment.controller.api.v1;

import com.apartment.assembler.datatable.SeriesResourceAssembler;
import com.apartment.resource.datatable.SeriesResource;
import com.apartment.service.series.SeriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/series/")
public class SeriesController {

    private final SeriesService seriesService;
    private final SeriesResourceAssembler seriesResourceAssembler;


    public SeriesController(SeriesService seriesService, SeriesResourceAssembler seriesResourceAssembler) {
        this.seriesService = seriesService;
        this.seriesResourceAssembler = seriesResourceAssembler;
    }

    @GetMapping("/list")
    public List<SeriesResource> list() {
        return seriesResourceAssembler.toResources(seriesService.findAll());
    }
}
