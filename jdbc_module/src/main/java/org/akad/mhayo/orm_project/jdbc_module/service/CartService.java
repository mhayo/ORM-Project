package org.akad.mhayo.orm_project.jdbc_module.service;


import org.akad.mhayo.orm_project.jdbc_module.dao.CartDAO;
import org.akad.mhayo.orm_project.jdbc_module.model.Cart;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    long queryStart;
    long queryEnd;

    @Autowired
    CartDAO cartDAO;

    public List<Cart> getAllCarts(){

        queryStart = System.currentTimeMillis();
        List<Cart> carts = cartDAO.getAllCarts();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","getAllCarts",queryEnd-queryStart);

        return carts;
    }

    public Cart getCartById(long id){

        queryStart = System.currentTimeMillis();
        Cart temp = cartDAO.getCartById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","getCartById",queryEnd-queryStart);

        return temp;
    }

    public void save(Cart cart){

        queryStart = System.currentTimeMillis();
        cartDAO.save(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","saveCart",queryEnd-queryStart);

    }

    public void update(Cart cart){

        queryStart = System.currentTimeMillis();
        cartDAO.update(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","updateCart",queryEnd-queryStart);

    }

    public void delete(Cart cart){

        queryStart = System.currentTimeMillis();
        cartDAO.delete(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","deleteCart",queryEnd-queryStart);

    }
}
