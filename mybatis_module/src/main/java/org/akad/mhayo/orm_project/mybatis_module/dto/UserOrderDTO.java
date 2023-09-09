package org.akad.mhayo.orm_project.mybatis_module.dto;

import lombok.Getter;
import lombok.Setter;
import org.akad.mhayo.orm_project.mybatis_module.model.Customer;
import org.akad.mhayo.orm_project.mybatis_module.model.Item;

import java.util.List;

@Setter
@Getter
public class UserOrderDTO {

    private long id;

    private Customer customer;

    private List<Item> itemList;

    private double sum;

}
