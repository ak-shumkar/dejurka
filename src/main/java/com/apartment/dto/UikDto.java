package com.apartment.dto;

import lombok.Data;

@Data
public class UikDto {

    private Long locationId;
    private String address;
    private String landmark;
    private String note;
    private String title;

    public UikDto() {
    }
}
