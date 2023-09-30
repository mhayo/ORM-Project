package org.akad.mhayo.orm_project.jdbc_module.service;


import org.akad.mhayo.orm_project.jdbc_module.dao.CartDAO;
import org.akad.mhayo.orm_project.jdbc_module.dao.UserOrderDAO;
import org.akad.mhayo.orm_project.jdbc_module.model.Cart;
import org.akad.mhayo.orm_project.jdbc_module.model.UserOrder;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserOrderService {

    long queryStart;
    long queryEnd;

    @Autowired
    UserOrderDAO userOrderDAO;

    @Autowired
    CartDAO cartDAO;

    public List<UserOrder> getAllOrders() {

        queryStart = System.currentTimeMillis();
        List<UserOrder> temp = userOrderDAO.getAllOrders();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc", "getAllOrders", queryEnd - queryStart);

        return temp;
    }


    public UserOrder getOrderById(long id) {

        queryStart = System.currentTimeMillis();
        UserOrder temp = userOrderDAO.getOrderById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc", "getOrderById", queryEnd - queryStart);

        return temp;
    }

    public void save(UserOrder userOrder) {

        queryStart = System.currentTimeMillis();
        userOrderDAO.save(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc", "save_userorder", queryEnd - queryStart);

    }

    public void update(UserOrder userOrder) {

        queryStart = System.currentTimeMillis();
        userOrderDAO.update(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc", "update_userorder", queryEnd - queryStart);

    }

    public void delete(UserOrder userOrder) {

        queryStart = System.currentTimeMillis();
        userOrderDAO.delete(userOrder.getId());
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc", "update_userorder", queryEnd - queryStart);

    }

    public UserOrder submit(long cartid) {


        Cart cart = cartDAO.getCartById(cartid);
        UserOrder userOrder = UserOrder.createFromCart(cart);
        queryStart = System.currentTimeMillis();
        userOrderDAO.save(userOrder);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc", "saveOrder", queryEnd - queryStart);

        return userOrder;

    }


}
