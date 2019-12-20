package com.apartment.model;

import lombok.Data;

@Data
public class Result {
    Long rank;
    String name;
    Integer solved;

    public Result(Long rank,String name, Integer solved) {
        this.rank = rank;
        this.name = name;
        this.solved = solved;
    }
}
