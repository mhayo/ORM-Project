package org.akad.mhayo.orm_project.jpa_module.dto;

import lombok.Getter;
import lombok.Setter;
import org.akad.mhayo.orm_project.jpa_module.model.Customer;
import org.akad.mhayo.orm_project.jpa_module.model.Item;

import java.util.List;

@Getter
@Setter
public class CartDTO {

    private long id;

    private List<Item> items;

    private Customer customer;


}