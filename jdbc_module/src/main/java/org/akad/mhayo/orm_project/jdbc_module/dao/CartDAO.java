package org.akad.mhayo.orm_project.jdbc_module.dao;

import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.model.Cart;
import org.akad.mhayo.orm_project.jdbc_module.model.Customer;
import org.akad.mhayo.orm_project.jdbc_module.model.Item;
import org.akad.mhayo.orm_project.util.exceptions.CartPersistenceException;
import org.akad.mhayo.orm_project.util.exceptions.CustomerException;
import org.akad.mhayo.orm_project.util.exceptions.DataBaseConnectException;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.akad.mhayo.orm_project.jdbc_module.dao.Sql.*;
import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.*;

@Component
public class CartDAO {

    public CartDAO() { /* standard constructor */}


    public List<Cart> getAllCarts()  {

        List<Cart> carts = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection())
        {

            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_ALL_CART_WITH_ITEM);
            ResultSet resultSet = preparedStatement1.executeQuery();

            while (resultSet.next()) {

                long currentID = resultSet.getLong("cartId"); 
                
                Optional<Cart> carts1 = carts.stream().filter(cart -> cart.getId() == currentID).findFirst();
                
                if(carts1.isEmpty()){
                    Cart cart = new Cart();
                    cart.setId(currentID);
                    cart.setCustomer(mapCustomer(resultSet));
                    cart.getItems().add(mapItem(resultSet).getId() == 0 ? null: mapItem(resultSet));
                    carts.add(cart);
                }else {
                    Optional.of(carts1).get().get().getItems().add(mapItem(resultSet).getId() == 0 ? null: mapItem(resultSet));
                }

            }
            preparedStatement1.close();


        } catch (SQLException ex) {
            throw new CartPersistenceException(FETCHING_ERROR);

        } catch (Exception e) {
            throw new DataBaseConnectException(CONNECTION_ERROR);
        }

        return carts;

    }


    public Cart getCartById(long id) {

        Cart cart = new Cart();

        try (Connection connection = DataBaseConnection.getConnection()) {

            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_ALL_CART_WITH_ITEM_BY_ID);
            preparedStatement1.setLong(1,id);
            ResultSet resultSet = preparedStatement1.executeQuery();

            while (resultSet.next()) {

                long currentID = resultSet.getLong("cartId");
                if(cart.getId() == currentID){
                    cart.getItems().add(mapItem(resultSet).getId() == 0 ? null: mapItem(resultSet));
                }else {
                    cart.setId(currentID);
                    cart.setCustomer(mapCustomer(resultSet));
                    cart.getItems().add(mapItem(resultSet).getId() == 0 ? null: mapItem(resultSet));
                }

            }
            preparedStatement1.close();


        } catch (SQLException ex) {
            throw new CartPersistenceException(FETCHING_ERROR);

        } catch (Exception e) {
            throw new DataBaseConnectException(CONNECTION_ERROR);
        }

        return cart;

    }


public void save(Cart cart){
        
    try (Connection connection = DataBaseConnection.getConnection()) {

        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART);
        preparedStatement.setLong(1,cart.getId());
        preparedStatement.setLong(2,cart.getCustomer().getId());

        preparedStatement.execute();
        connection.commit();
        preparedStatement.close();

    } catch (SQLException e) {
        throw new CartPersistenceException(CARTID_ERROR + cart.getId());
    } catch (Exception e) {
        throw new DataBaseConnectException(CONNECTION_ERROR);
    }

}

    public void update(Cart cart){

        try (Connection connection = DataBaseConnection.getConnection()){

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART)) {
                preparedStatement.setLong(1, cart.getId());
                preparedStatement.setLong(2, cart.getCustomer().getId());
                preparedStatement.setLong(3, cart.getId());

                preparedStatement.execute();
                connection.commit();
            }

        } catch (SQLException e) {
            throw new CartPersistenceException(CARTID_ERROR + cart.getId());
        } catch (Exception e) {
            throw new DataBaseConnectException(CONNECTION_ERROR);
        }

    }

    public void delete(Cart cart){


        try (Connection connection = DataBaseConnection.getConnection()){

            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART)) {
                preparedStatement.setLong(1, cart.getId());

                preparedStatement.execute();
                connection.commit();
            }

        } catch (SQLException e) {
            throw new CartPersistenceException(CARTID_ERROR + cart.getId());
        } catch (Exception e) {
            throw new DataBaseConnectException(CONNECTION_ERROR);
        }

    }
    
    // helper methods
    
    private Customer mapCustomer(ResultSet resultSet){
        
        Customer customer = new Customer();

        try {
            customer.setId(resultSet.getLong("customer_id"));
            customer.setUsername(resultSet.getString("username"));
            customer.setName(resultSet.getString("name"));
            customer.setSurname(resultSet.getString("surname"));
            customer.setBirthday(resultSet.getDate("birthday"));
            customer.setZipcode(resultSet.getString("zipcode"));
            customer.setCity(resultSet.getString("city"));
            customer.setStreet(resultSet.getString("street"));
            customer.setHousenumber(resultSet.getInt("housenumber"));
            customer.setCountry(resultSet.getString("country"));
        } catch (SQLException e) {
            throw new CustomerException(FETCHING_CUSTOMER_ERROR);
        }
        return customer; 
        
    }


    private static Item mapItem(ResultSet resultSet1) throws SQLException {

        Item item = new Item();

        item.setId(resultSet1.getLong("itemid"));
        item.setDescription(resultSet1.getString("description"));
        item.setPrice(resultSet1.getDouble("price"));
        return item;
    }

}
