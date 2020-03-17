package com.apartment.resource.datatable;

import com.apartment.model.Image;
import com.apartment.resource.datatable.base.BaseResource;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class HouseResource extends BaseResource {
    private String title;
    private LocationResource locationResource;
    private SeriesResource seriesResource;
    private String address;
    private String description;
    private Integer rooms;
    private Integer marketType;
    private Integer houseType;
    private String houseNumber;
    private String area;
    private Integer price;
    private String currency;
    private String phoneNumber;
    private List<Image> images;
    private Integer priceFull;
    private String floor;
    private String floorFull;
    private String nameOwner;
    private String nameContract;
}
