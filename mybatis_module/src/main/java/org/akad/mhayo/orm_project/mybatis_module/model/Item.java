package org.akad.mhayo.orm_project.mybatis_module.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private long id;
    private String description;

    private double price;

    public Item(){

    }

    public Item(long id) {
        this.id = id;
    }
}
