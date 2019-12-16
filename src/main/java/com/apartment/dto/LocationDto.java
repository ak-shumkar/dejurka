package com.apartment.dto;

import lombok.Data;

@Data
public class LocationDto {

    private Long parentId;
    private Integer locationType;
    private Integer locationLevel;
    private String title;

    public LocationDto(){

    }


}
