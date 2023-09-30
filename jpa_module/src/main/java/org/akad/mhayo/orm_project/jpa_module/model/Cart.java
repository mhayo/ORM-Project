package org.akad.mhayo.orm_project.jpa_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch=FetchType.LAZY)
    private List<Item> items;

    @ManyToOne
    private Customer customer;

    public Cart(){

    }

    public Cart(long id, List<Item> items, Customer customer) {
        this.id = id;
        this.items = items;
        this.customer = customer;
    }

    public void addItem(Item item){

        this.items.add(item);
    }
}