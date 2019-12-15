package com.election.components;

public interface Selectable {

    String getSelectorId();

    String getSelectorTitle();

    default public String getClassName() {
        return "";
    }
}
