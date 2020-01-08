package com.apartment.repository;

import com.apartment.model.House;
import com.apartment.model.QHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public interface HouseRepository extends JpaRepository<House, Long>,
        QuerydslPredicateExecutor<House>, QuerydslBinderCustomizer<QHouse> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QHouse root) {

    }

}
