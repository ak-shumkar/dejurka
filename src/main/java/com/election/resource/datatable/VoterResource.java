package com.election.resource.datatable;

import com.election.resource.datatable.base.BaseResource;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VoterResource extends BaseResource {

    private String uikTitle;
    private Long uikId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer gender;
    private String position;
    private String phone;
    private String birthDate;
    private String pin;
    private String age;
}
