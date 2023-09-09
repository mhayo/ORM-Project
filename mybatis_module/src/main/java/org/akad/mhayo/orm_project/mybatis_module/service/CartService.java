package org.akad.mhayo.orm_project.mybatis_module.service;

import org.akad.mhayo.orm_project.mybatis_module.mapper.CartMapper;
import org.akad.mhayo.orm_project.mybatis_module.model.Cart;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    long queryStart;
    long queryEnd;

    public List<Cart> getAllCarts(){

        queryStart = System.currentTimeMillis();
        List<Cart> temp = cartMapper.getAllCarts();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","getAllCarts",queryEnd-queryStart);

        return temp;

    }

    public Cart getCartById(long id){

        queryStart = System.currentTimeMillis();
        Cart temp = cartMapper.getCartById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","getCartById",queryEnd-queryStart);

        return temp;
    }

    public void save(Cart cart){

        queryStart = System.currentTimeMillis();
        cartMapper.insertCart(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","save_cart",queryEnd-queryStart);

    }

    public void update(Cart cart){

        queryStart = System.currentTimeMillis();
        cartMapper.updateCart(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","update_cart",queryEnd-queryStart);

    }

    public void delete(Cart cart){

        queryStart = System.currentTimeMillis();
        cartMapper.deleteCart(cart.getId());
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","delete_cart",queryEnd-queryStart);

    }
}