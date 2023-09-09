package org.akad.mhayo.orm_project.jdbc_module.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class UserOrder {

    private long id;

    private Customer customer;

    private List<Item> itemList;

    private double sum;


    public UserOrder(){

        this.itemList = new ArrayList<>();
    }

    public static UserOrder createFromCart(Cart cart){

        UserOrder userOrder = new UserOrder();
        userOrder.itemList =  List.copyOf(cart.getItems());

        userOrder.customer = cart.getCustomer();
        userOrder.sum = userOrder.itemList.stream().mapToDouble(Item::getPrice).sum();

        return userOrder ;

    }
}
