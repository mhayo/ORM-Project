package org.akad.mhayo.orm_project.jpa_module.service;

import jakarta.transaction.Transactional;
import org.akad.mhayo.orm_project.jpa_module.model.Cart;
import org.akad.mhayo.orm_project.jpa_module.repository.CartRepository;
import org.akad.mhayo.orm_project.jpa_module.repository.CustomerRepository;
import org.akad.mhayo.orm_project.jpa_module.repository.ItemRepository;
import org.akad.mhayo.orm_project.util.Measurement;
import org.akad.mhayo.orm_project.util.exceptions.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    long queryStart;
    long queryEnd;

    public List<Cart> getAllCarts(){

        queryStart = System.currentTimeMillis();
        List<Cart> temp = cartRepository.findAll();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","getAllCarts",queryEnd-queryStart);

        return temp;

    }

    public Cart getCartById(long id){

        Cart cart;
        queryStart = System.currentTimeMillis();
        Optional<Cart> temp = cartRepository.findById(id);
        if(temp.isPresent()){
            cart = temp.get();
        }else {
            throw new CartNotFoundException("Cart not found with id: " + id);
        }
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","getCartById",queryEnd-queryStart);

        return cart;
    }

    public void save(Cart cart){

        queryStart = System.currentTimeMillis();
        cartRepository.save(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","save_cart",queryEnd-queryStart);

    }

    public void update(Cart cart){

            queryStart = System.currentTimeMillis();
            cartRepository.save(cart);
            queryEnd = System.currentTimeMillis();

            Measurement.writeToCsv("jpa","update_cart",queryEnd-queryStart);

    }

    public void delete(Cart cart){

        queryStart = System.currentTimeMillis();
        cartRepository.delete(cart);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","delete_cart",queryEnd-queryStart);

    }

}