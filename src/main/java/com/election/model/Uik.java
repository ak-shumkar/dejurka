package com.election.model;

import com.election.components.Selectable;
import com.election.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ref_uik")
@Accessors(chain = true)
public class Uik extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -6526041027603232427L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "note")
    private String note;

    public Uik() {
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
