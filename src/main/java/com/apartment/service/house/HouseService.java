package com.apartment.service.house;

import com.apartment.dto.HouseDto;
import com.apartment.model.House;
import com.apartment.model.Image;
import com.apartment.model.Location;
import com.apartment.model.QHouse;
import com.apartment.repository.HouseRepository;
import com.apartment.service.FileStorageService;
import com.apartment.service.location.LocationService;
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


    protected HouseService(LocationService locationService, HouseRepository houseRepository, FileStorageService fileStorageService) {
        this.locationService = locationService;
        this.houseRepository = houseRepository;
        this.fileStorageService = fileStorageService;
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
        BeanUtils.copyProperties(houseDto, house,"locationId");
        house.setLocation(location);
        return houseRepository.save(house);
    }

    public House findById(Long id){
        return houseRepository.findById(id).orElse(null);
    }

    public Page<House> findAll(Pageable pageable){
        return houseRepository.findAll(pageable);
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

        if(Objects.nonNull(builder.getValue()))
            return this.houseRepository.findAll(Objects.requireNonNull(builder.getValue()),pageable);
        else
            return this.houseRepository.findAll(pageable);
    }

    public Boolean delete(House house){
        houseRepository.delete(house);
        return true;
    }
}
