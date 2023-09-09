package org.akad.mhayo.orm_project.jdbc_module.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cart {

    private long id;

    private List<Item> items;

    private Customer customer;

    public Cart(){

        this.items = new ArrayList<>();
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
