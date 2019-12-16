package com.apartment.resource;

import com.apartment.components.Selectable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation = "list")
public class SelectOptionResource extends ResourceSupport implements Selectable {
    @JsonProperty("id")
    public String id;

    private String title;

    public SelectOptionResource() {
    }

    public String getTitle() {
        return this.title;
    }

    public SelectOptionResource setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getSelectorId() {
        return id;
    }

    @Override
    public String getSelectorTitle() {
        return title;
    }
}
