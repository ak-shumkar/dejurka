package com.apartment.dto;

import com.apartment.model.Location;
import com.apartment.model.Role;
import com.apartment.model.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class HouseDto {
    private String title;
    private Long locationId;
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
}
