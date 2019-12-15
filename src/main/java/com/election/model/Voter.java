package com.election.model;

import com.election.components.Selectable;
import com.election.model.base.BaseEntity;
import com.election.model.base.TimedEntity;
import edu.umd.cs.findbugs.annotations.CleanupObligation;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table (name = "voter")
@Accessors(chain = true)
public class Voter extends TimedEntity implements Selectable {

    private static final long serialVersionUID = -5937305845996943244L;

    @ManyToOne
    @JoinColumn (name = "uik_id")
    private Uik uik;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "middle_name")
    private String middleName;

    @Column (name = "gender")
    private Integer gender;

    @Column (name = "position")
    private String position;

    @Column (name = "phone")
    private String phone;

    @Column (name = "enabled")
    private Boolean enabled;

    @Column (name = "date_of_birth")
    private LocalDate birthDate;

    @Column (name = "pin")
    private String pin;

    public Voter(){
    }

    public Voter(Uik uik, String firstName, String lastName, String middleName, Integer gender, String position, String phone, Boolean enabled, LocalDate birthDate, String pin) {
        this.uik = uik;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.position = position;
        this.phone = phone;
        this.enabled = enabled;
        this.birthDate = birthDate;
        this.pin = pin;
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
