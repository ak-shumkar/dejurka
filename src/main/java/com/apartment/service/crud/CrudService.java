package com.apartment.service.crud;

import com.apartment.model.base.BaseEntity;

import javax.validation.constraints.NotNull;

public interface CrudService<E extends BaseEntity> {

    @NotNull
    E save(
            @NotNull E e
    );

    void delete(
            @NotNull E e
    );
}
