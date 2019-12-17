package com.apartment.model;

import com.apartment.components.Selectable;
import com.apartment.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Data
@Entity
@Table(name = "image")
@Accessors(chain = true)
public class Image extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -5937305845996943244L;

    @Column(name = "path")
    private String path;

    public Image(){

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
