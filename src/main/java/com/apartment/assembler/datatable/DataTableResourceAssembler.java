package com.apartment.assembler.datatable;

import com.apartment.model.base.BaseEntity;
import com.apartment.resource.datatable.base.BaseResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

abstract class DataTableResourceAssembler<T extends BaseEntity, D extends BaseResource> extends ResourceAssemblerSupport<T, D> {

    public DataTableResourceAssembler(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected D createResourceWithId(Object id, T entity) {
        D d = super.createResourceWithId(id, entity);
        d.setUuid(entity.getId().toString());
        return d;
    }


}
