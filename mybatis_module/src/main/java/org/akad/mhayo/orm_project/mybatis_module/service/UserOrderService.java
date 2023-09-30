package org.akad.mhayo.orm_project.mybatis_module.service;

import org.akad.mhayo.orm_project.mybatis_module.mapper.CartMapper;
import org.akad.mhayo.orm_project.mybatis_module.mapper.UserOrderMapper;
import org.akad.mhayo.orm_project.mybatis_module.model.Cart;
import org.akad.mhayo.orm_project.mybatis_module.model.UserOrder;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {


    @Autowired
    UserOrderMapper userOrderMapper;

    @Autowired
    CartMapper cartMapper;

    long queryStart;
    long queryEnd;

    public List<UserOrder> getAllOrders() {

        queryStart = System.currentTimeMillis();
        List<UserOrder> temp = userOrderMapper.getAllOrders();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis", "getAllOrders", queryEnd - queryStart);

        return temp;
    }

    public UserOrder getOrderById(long id) {

        queryStart = System.currentTimeMillis();
        UserOrder temp = userOrderMapper.getOrderById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis", "getOrderById", queryEnd - queryStart);

        return temp;
    }

    public void save(UserOrder userOrder) {

        queryStart = System.currentTimeMillis();
        userOrderMapper.save(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis", "save_userorder", queryEnd - queryStart);

    }


    public void update(UserOrder userOrder) {

        queryStart = System.currentTimeMillis();
        userOrderMapper.update(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis", "update_userorder", queryEnd - queryStart);

    }

    public void delete(UserOrder userOrder) {

        queryStart = System.currentTimeMillis();
        userOrderMapper.delete(userOrder.getId());
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis", "delete_userorder", queryEnd - queryStart);

    }


    public UserOrder submit(long cartid) {


        Cart cart = cartMapper.getCartById(cartid);
        UserOrder userOrder = UserOrder.createFromCart(cart);
        queryStart = System.currentTimeMillis();
        userOrderMapper.save(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis", "saveOrder", queryEnd - queryStart);

        return userOrder;

    }
}