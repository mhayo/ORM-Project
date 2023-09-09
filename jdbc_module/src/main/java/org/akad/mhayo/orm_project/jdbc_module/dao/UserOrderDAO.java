package org.akad.mhayo.orm_project.jdbc_module.dao;

import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.model.Customer;
import org.akad.mhayo.orm_project.jdbc_module.model.Item;
import org.akad.mhayo.orm_project.jdbc_module.model.UserOrder;
import org.akad.mhayo.orm_project.util.exceptions.CustomerException;
import org.akad.mhayo.orm_project.util.exceptions.ItemException;
import org.akad.mhayo.orm_project.util.exceptions.OrderException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.akad.mhayo.orm_project.jdbc_module.dao.Sql.*;
import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.*;

@Component
public class UserOrderDAO {


    public UserOrderDAO(){ /* standard constructor */}


    public List<UserOrder> getAllOrders(){

        List<UserOrder> userOrders = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()) {

            ResultSet userOrderResult;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERORDERS)) {

                userOrderResult = preparedStatement.executeQuery();

                while(userOrderResult.next()){

                    long currentID = userOrderResult.getLong("id");

                    if(userOrders.stream().filter(userOrder -> userOrder.getId() == currentID).toList().isEmpty()){

                        UserOrder userOrder = new UserOrder();
                        userOrder.setId(userOrderResult.getLong("id"));
                        userOrder.setSum(userOrderResult.getDouble("sum"));
                        userOrder.setCustomer(mapCustomer(userOrderResult));
                        userOrder.getItemList().add(mapItem(userOrderResult));
                        userOrders.add(userOrder);

                    } else {
                        userOrders.stream()
                                .filter(userOrder -> userOrder.getId() == currentID)
                                .toList()
                                .get(0).getItemList()
                                .add(mapItem(userOrderResult));
                    }

                }
            }

        } catch (SQLException e) {
            throw new OrderException(FETCHING_ORDER_ERROR);
        }

        return userOrders;

    }


    public UserOrder getOrderById(long id){

        UserOrder userOrder = new UserOrder();

        try (Connection connection = DataBaseConnection.getConnection()){

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERORDER_BY_ID)) {

                preparedStatement.setLong(1, id);
                ResultSet  userOrderResult = preparedStatement.executeQuery();

                while(userOrderResult.next()){

                    long currentID = userOrderResult.getLong("id");

                    if(userOrder.getId() == currentID){
                        userOrder.getItemList().add(mapItem(userOrderResult));
                    }else {
                        userOrder.setId(userOrderResult.getLong("id"));
                        userOrder.setSum(userOrderResult.getDouble("sum"));
                        userOrder.setCustomer(mapCustomer(userOrderResult));
                        userOrder.getItemList().add(mapItem(userOrderResult));

                    }

                }

            }

        } catch (SQLException e) {
            throw new OrderException(FETCHING_ORDER_ERROR);
        }

        return userOrder;

    }

    public void save(UserOrder userOrder ){

        try (Connection connection = DataBaseConnection.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERORDER)) {

                preparedStatement.setLong(1, userOrder.getId());
                preparedStatement.setLong(2, userOrder.getCustomer().getId());
                preparedStatement.setDouble(3, userOrder.getSum());

                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new OrderException(INSERT_ORDER_ERROR);
        }

    }


    public void update(UserOrder userOrder ){


        try (Connection connection = DataBaseConnection.getConnection()){

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERORDER)) {
                preparedStatement.setLong(1, userOrder.getId());
                preparedStatement.setLong(2, userOrder.getCustomer().getId());
                preparedStatement.setDouble(3, userOrder.getSum());
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new OrderException(UPDATE_ORDER_ERRR);
        }

    }

    public void delete(long id ){


        try (Connection connection = DataBaseConnection.getConnection()){

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERORDER)) {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new OrderException(DELETE_ORDER_ERROR);
        }

    }

    // helper methods

    private Customer mapCustomer(ResultSet resultset){

        Customer customer = new Customer();

        try {
            customer.setId(resultset.getLong("customer_id"));
            customer.setUsername(resultset.getString("username"));
            customer.setBirthday(resultset.getDate("birthday"));
            customer.setName(resultset.getString("name"));
            customer.setSurname(resultset.getString("surname"));
            customer.setCountry(resultset.getString("country"));
            customer.setZipcode(resultset.getString("zipcode"));
            customer.setCity(resultset.getString("city"));
            customer.setStreet(resultset.getString("street"));
            customer.setHousenumber(resultset.getInt("housenumber"));
        } catch (SQLException e) {
            throw new CustomerException(FETCHING_CUSTOMER_ERROR);
        }

        return customer;

    }

    private Item mapItem(ResultSet resultSet){

        Item item = new Item();

        try {
            item.setId(resultSet.getLong("itemid"));
            item.setDescription(resultSet.getString("description"));
            item.setPrice(resultSet.getDouble("price"));
        } catch (SQLException e) {
            throw new ItemException(FETCHING_ITEM_ERROR);
        }

        return item;

    }


}
