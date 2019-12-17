package com.apartment.service.house;

import com.apartment.dto.HouseDto;
import com.apartment.model.House;
import com.apartment.model.Image;
import com.apartment.model.Location;
import com.apartment.repository.HouseRepository;
import com.apartment.service.FileStorageService;
import com.apartment.service.location.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
