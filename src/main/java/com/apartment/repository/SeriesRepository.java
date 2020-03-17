package com.apartment.repository;

import com.apartment.model.House;
import com.apartment.model.QHouse;
import com.apartment.model.QSeries;
import com.apartment.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public interface SeriesRepository extends JpaRepository<Series, Long>,
        QuerydslPredicateExecutor<Series>, QuerydslBinderCustomizer<QSeries> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QSeries root) {

    }

}
