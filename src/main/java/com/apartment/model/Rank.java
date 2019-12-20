package com.apartment.model;

import com.apartment.components.Selectable;
import com.apartment.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ranklist")
@Accessors(chain = true)
public class Rank extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -5937305845996943244L;

    @Column(name = "solved")
    private Integer solved;

    @NotNull
    @Column(name = "username")
    private String username;


    public Rank(){

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
