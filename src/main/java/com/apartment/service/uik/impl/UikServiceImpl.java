package com.apartment.service.uik.impl;

import com.apartment.dto.UikDto;
import com.apartment.model.QUik;
import com.apartment.model.Uik;
import com.apartment.repository.UikRepository;
import com.apartment.service.location.LocationService;
import com.apartment.service.uik.UikService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UikServiceImpl implements UikService {

    private final UikRepository uikRepository;
    private final LocationService locationService;

    public UikServiceImpl(UikRepository uikRepository, LocationService locationService) {
        this.uikRepository = uikRepository;
        this.locationService = locationService;
    }

    @Override
    public Uik create(UikDto uikDto) {
        return uikRepository.save(
                new Uik()
                        .setAddress(uikDto.getAddress())
                        .setLandmark(uikDto.getLandmark())
                        .setNote(uikDto.getNote())
                        .setTitle(uikDto.getTitle())
                        .setLocation(locationService.findById(uikDto.getLocationId()))
        );
    }

    @Override
    public void edit(Uik src, UikDto target) {

        uikRepository.save(src
                .setTitle(target.getTitle())
                .setAddress(target.getAddress())
                .setLandmark(target.getLandmark())
                .setNote(target.getNote())
                .setLocation(locationService.findById(target.getLocationId()))
        );
    }

    @Override
    public Uik findById(Long id) {
        return uikRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Uik> list(Long location) {

        final QUik root = QUik.uik;
        final BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(location))
            builder.and(root.location.id.eq(location));

        if(Objects.nonNull(builder.getValue()))
            return this.uikRepository.findAll(Objects.requireNonNull(builder.getValue()));
        else
            return this.uikRepository.findAll();
    }

    @Override
    public List<Uik> listByUser(Long userId) {
        return uikRepository.findAllByUser(userId);
    }
}
