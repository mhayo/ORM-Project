package org.akad.mhayo.orm_project.jpa_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    private double price;

    public Item(){

    }

    public Item(long id) {
        this.id = id;
    }
}
