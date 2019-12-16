package com.apartment.model;

import com.apartment.components.Selectable;
import com.apartment.model.base.TimedEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.security.Permission;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User extends TimedEntity implements UserDetails, Selectable {

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_voter",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "voter_id", referencedColumnName = "id")})
    private List<Voter> voters;

    @ManyToMany
    @JoinTable(name = "m2m_user_location_permission",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id", referencedColumnName = "id")})
    private List<Location> locations;

    @Transient
    private Set<Role> sAvailableRoles = new HashSet<>();

    @Transient
    private Set<Role> sRoles = new HashSet<>();

    @Transient
    private Set<Permission> sPermissions = new HashSet<>();

    @Transient
    private Collection<? extends GrantedAuthority> sAuthorities = new HashSet<>();

    public User() {

    }

    public User(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String getSelectorId() {
        return null;
    }

    @Override
    public String getSelectorTitle() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public Set<Role> getsAvailableRoles() {
        return sAvailableRoles;
    }

    public User setsAvailableRoles(Set<Role> sAvailableRoles) {
        this.sAvailableRoles = sAvailableRoles;
        return this;
    }

    public Set<Role> getsRoles() {
        return sRoles;
    }

    public User setsRoles(Set<Role> sRoles) {
        this.sRoles = sRoles;
        return this;
    }

    public Set<Permission> getsPermissions() {
        return sPermissions;
    }

    public User setsPermissions(Set<Permission> sPermissions) {
        this.sPermissions = sPermissions;
        return this;
    }

    public Collection<? extends GrantedAuthority> getsAuthorities() {
        return sAuthorities;
    }

    public User setsAuthorities(Collection<? extends GrantedAuthority> sAuthorities) {
        this.sAuthorities = sAuthorities;
        return this;
    }
}
