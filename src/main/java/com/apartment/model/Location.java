package com.apartment.model;

import com.apartment.components.Selectable;
import com.apartment.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ref_location")
@Accessors(chain = true)
public class Location extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -5937305845996943244L;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parent;

    @Column(name = "location_type")
    private Integer locationType;

    @Column(name = "location_level")
    private Integer locationLevel;

    @NotNull
    @Column(name = "title")
    private String title;


    public Location(){

    }


    @Override
    public String getSelectorId() {
        return null;
    }

    @Override
    public String getSelectorTitle() {
        return null;
    }
}
