package com.election.assembler.datatable;

import com.election.model.base.BaseEntity;
import com.election.resource.datatable.base.BaseResource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

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
