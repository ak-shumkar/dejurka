package com.election.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class VoterDto {
    private Long uikId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer gender;
    private String birthDate;
    private String position;
    private String phone;

    //private Boolean enabled;
    //private String pin;

    public VoterDto(){}
}
