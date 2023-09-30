package org.akad.mhayo.orm_project.jpa_module.service;


import org.akad.mhayo.orm_project.jpa_module.model.Cart;
import org.akad.mhayo.orm_project.jpa_module.model.UserOrder;
import org.akad.mhayo.orm_project.jpa_module.repository.CartRepository;
import org.akad.mhayo.orm_project.jpa_module.repository.UserOrderRepository;
import org.akad.mhayo.orm_project.util.Measurement;
import org.akad.mhayo.orm_project.util.exceptions.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserOrderService {

    long queryStart;
    long queryEnd;

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    CartRepository cartRepository;

    public List<UserOrder> getAllOrders(){

        queryStart = System.currentTimeMillis();
        List<UserOrder> temp = userOrderRepository.findAll();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","getAllOrders",queryEnd-queryStart);

        return temp;
    }

    public UserOrder getOrderById(long id){

        queryStart = System.currentTimeMillis();
        Optional<UserOrder> temp = userOrderRepository.findById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","getOrderById",queryEnd-queryStart);

        return temp.orElse(null);
    }

    public void save(UserOrder userOrder){

        queryStart = System.currentTimeMillis();
        userOrderRepository.save(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","save_userorder",queryEnd-queryStart);

    }


    public void update(UserOrder userOrder){

        queryStart = System.currentTimeMillis();
        userOrderRepository.save(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","update_userorder",queryEnd-queryStart);

    }

    public void delete(UserOrder userOrder){

        queryStart = System.currentTimeMillis();
        userOrderRepository.delete(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","delete_userorder",queryEnd-queryStart);

    }

    public UserOrder submit(long cartid){


        Optional<Cart> cart = cartRepository.findById(cartid);

            if(cart.isPresent()){
                UserOrder   userOrder = UserOrder.createFromCart(cart.get());
                queryStart = System.currentTimeMillis();
                userOrderRepository.save(userOrder);
                 queryEnd = System.currentTimeMillis();
                 Measurement.writeToCsv("jpa","saveOrder",queryEnd-queryStart);

        return userOrder;

        }else {
            throw new CartNotFoundException("Cart not found with id: " + cartid);
        }
    }

}
