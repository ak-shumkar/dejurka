package com.apartment.service.house;

import com.apartment.dto.HouseDto;
import com.apartment.model.*;
import com.apartment.repository.HouseRepository;
import com.apartment.service.FileStorageService;
import com.apartment.service.location.LocationService;
import com.apartment.service.series.SeriesService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HouseService  {

    private final LocationService locationService;
    private final HouseRepository houseRepository;
    private final FileStorageService fileStorageService;
    private final SeriesService seriesService;


    protected HouseService(LocationService locationService, HouseRepository houseRepository, FileStorageService fileStorageService, SeriesService seriesService) {
        this.locationService = locationService;
        this.houseRepository = houseRepository;
        this.fileStorageService = fileStorageService;
        this.seriesService = seriesService;
    }

    public House create(@NotNull HouseDto houseDto, MultipartFile[] images) throws Exception {
        List<Image> paths = new ArrayList<>();
        for (MultipartFile image : images) {
            String tmp = String.valueOf(new Date().getTime());
            paths.add(new Image().setPath(fileStorageService.saveFile(tmp, image)));
        }

        return createAndUpdate(houseDto,new House().setImages(paths));
    }

    public House update(@NotNull HouseDto houseDto, @NotNull House house) {
        return createAndUpdate(houseDto,house);
    }

    public House createAndUpdate(HouseDto houseDto,House house){
        Location location = locationService.findById(houseDto.getLocationId());
        Series series = null;
        if (houseDto.getSeriesId() != null) {
            series = seriesService.findById(houseDto.getSeriesId());
        }
        BeanUtils.copyProperties(houseDto, house,"locationId");
        house.setLocation(location);
        house.setSeries(series);
        return houseRepository.save(house);
    }

    public House findById(Long id){
        return houseRepository.findById(id).orElse(null);
    }

    public List<House> findAll(){
        return houseRepository.findAll();
    }

    public Page<House> filter(HouseDto houseDto,Pageable pageable){
        final QHouse root = QHouse.house;
        final BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(houseDto.getLocationId()))
            builder.and(root.location.id.eq(houseDto.getLocationId()));
        if (Objects.nonNull(houseDto.getAddress()))
            builder.and(root.address.eq(houseDto.getAddress()));
        if (Objects.nonNull(houseDto.getArea()))
            builder.and(root.area.eq(houseDto.getArea()));
        if (Objects.nonNull(houseDto.getCurrency()))
            builder.and(root.currency.eq(houseDto.getCurrency()));
        if (Objects.nonNull(houseDto.getDescription()))
            builder.and(root.description.eq(houseDto.getDescription()));
        if (Objects.nonNull(houseDto.getHouseNumber()))
            builder.and(root.houseNumber.eq(houseDto.getHouseNumber()));
        if (Objects.nonNull(houseDto.getHouseType()))
            builder.and(root.houseType.eq(houseDto.getHouseType()));
        if (Objects.nonNull(houseDto.getMarketType()))
            builder.and(root.marketType.eq(houseDto.getMarketType()));
        if (Objects.nonNull(houseDto.getPhoneNumber()))
            builder.and(root.phoneNumber.eq(houseDto.getPhoneNumber()));
        if (Objects.nonNull(houseDto.getRooms()))
            builder.and(root.rooms.eq(houseDto.getRooms()));
        if (Objects.nonNull(houseDto.getPrice()))
            builder.and(root.price.eq(houseDto.getPrice()));
        if (Objects.nonNull(houseDto.getTitle()))
            builder.and(root.title.eq(houseDto.getTitle()));
        if (Objects.nonNull(houseDto.getSeriesId()))
            builder.and(root.series.id.eq(houseDto.getSeriesId()));

        if (Objects.nonNull(houseDto.getPriceFull()))
            builder.and(root.priceFull.eq(houseDto.getPriceFull()));

        if (Objects.nonNull(houseDto.getFloor()))
            builder.and(root.floor.eq(houseDto.getFloor()));

        if (Objects.nonNull(houseDto.getFloorFull()))
            builder.and(root.floorFull.eq(houseDto.getFloorFull()));

        if (Objects.nonNull(houseDto.getNameOwner()))
            builder.and(root.floorFull.eq(houseDto.getNameOwner()));

        if (Objects.nonNull(houseDto.getNameContract()))
            builder.and(root.floorFull.eq(houseDto.getNameContract()));
        if(Objects.nonNull(builder.getValue()))
            return this.houseRepository.findAll(Objects.requireNonNull(builder.getValue()),pageable);
        else
            return this.houseRepository.findAll(pageable);
    }

    public Boolean delete(House house){
        houseRepository.delete(house);
        return true;
    }

    public House save(House house){
        return houseRepository.save(house);
    }
}
