package com.apartment.repository;

import com.apartment.model.Location;
import com.apartment.model.QLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public interface LocationRepository extends JpaRepository<Location, Long>,
        QuerydslPredicateExecutor<Location>, QuerydslBinderCustomizer<QLocation> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QLocation root) {
    }
}
