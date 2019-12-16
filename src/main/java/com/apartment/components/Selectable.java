package com.apartment.components;

public interface Selectable {

    String getSelectorId();

    String getSelectorTitle();

    default public String getClassName() {
        return "";
    }
}
