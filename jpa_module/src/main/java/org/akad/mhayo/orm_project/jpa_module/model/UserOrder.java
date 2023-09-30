package org.akad.mhayo.orm_project.jpa_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Item> itemList;

    private double sum;


    public UserOrder(){ /* standard constructor */ }

    public static UserOrder createFromCart(Cart cart){

        UserOrder userOrder = new UserOrder();
        userOrder.itemList =  List.copyOf(cart.getItems());

        userOrder.customer = cart.getCustomer();
        userOrder.sum = userOrder.itemList.stream().mapToDouble(Item::getPrice).sum();

        return userOrder ;

    }
}