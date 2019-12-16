package com.apartment.resource.datatable.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

public class BaseResource extends ResourceSupport implements Serializable {

    @JsonProperty
    public Long id;

    public String uuid;


    public Long getBaseId() {
        return id;
    }

    public void setBaseId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
