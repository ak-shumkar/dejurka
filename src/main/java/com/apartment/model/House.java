package com.apartment.model;

import com.apartment.components.Selectable;
import com.apartment.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "house")
@Accessors(chain = true)
public class House extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -5937305845996943244L;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name = "house_image",
            joinColumns = {@JoinColumn(name = "houses_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "images_id", referencedColumnName = "id")})
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "address")
    private String address;

    @Column(name = "dsc")
    private String description;

    @Column(name = "rooms")
    private Integer rooms;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", referencedColumnName = "id")
    private Series series;

    @Column(name = "market_type")
    private Integer marketType;

    @Column(name = "house_type")
    private Integer houseType;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "area")
    private String area;

    @Column(name = "price")
    private Integer price;

    @Column(name = "price_full")
    private Integer priceFull;

    @Column(name = "currency")
    private String currency;

    @Column(name = "floor")
    private String floor;

    @Column(name = "floor_full")
    private String floorFull;

    @Column(name = "name_owner")
    private String nameOwner;

    @Column(name = "name_contract")
    private String nameContract;

    @Column(name = "phone_number")
    private String phoneNumber;

    public House(){

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
