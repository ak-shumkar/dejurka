package com.apartment.service.series;

import com.apartment.model.Series;
import com.apartment.repository.SeriesRepository;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {

    private final SeriesRepository repository;

    public SeriesService(SeriesRepository repository) {
        this.repository = repository;
    }

    public Series findById(Long id){
        return repository.findById(id).orElse(null);
    }
}
