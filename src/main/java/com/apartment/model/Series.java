package com.apartment.model;

import com.apartment.components.Selectable;
import com.apartment.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "series")
@Accessors(chain = true)
public class Series extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -5937305845996943244L;

    @Column(name = "name")
    private String name;

    @Override
    public String getSelectorId() {
        return null;
    }

    @Override
    public String getSelectorTitle() {
        return null;
    }
}
