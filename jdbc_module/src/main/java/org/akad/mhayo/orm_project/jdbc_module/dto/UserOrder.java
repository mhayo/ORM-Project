package org.akad.mhayo.orm_project.jdbc_module.dto;

import lombok.Getter;
import lombok.Setter;
import org.akad.mhayo.orm_project.jdbc_module.model.Customer;
import org.akad.mhayo.orm_project.jdbc_module.model.Item;

import java.util.List;

@Getter
@Setter
public class UserOrder {

    private long id;

    private Customer customer;

    private List<Item> itemList;

    private double sum;

}
