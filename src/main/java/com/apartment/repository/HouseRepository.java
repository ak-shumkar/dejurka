package com.apartment.repository;

import com.apartment.model.House;
import com.apartment.model.Role;
import com.apartment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {

}
