package org.akad.mhayo.orm_project.jdbc_module.dao;

import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.model.Customer;
import org.akad.mhayo.orm_project.util.exceptions.CustomerException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.akad.mhayo.orm_project.jdbc_module.dao.Sql.*;
import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.*;

@Component
public class CustomerDAO {

    Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerDAO.class);

    public CustomerDAO(){
        //Standard constructor
   }


   public Customer getCustomerById(long id){

       Customer customer = new Customer();
       
       try (Connection connection = DataBaseConnection.getConnection()){
           ResultSet resultSet;
           try (PreparedStatement preparedStatement
                        = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
               preparedStatement.setLong(1, id);
               resultSet = preparedStatement.executeQuery();

               while(resultSet.next()){
                   customer = mapCustomer(resultSet);
               }

           }

       } catch (SQLException e) {
           throw new CustomerException(FETCHING_CUSTOMER_ERROR);
       }

       return customer;
   }

   public List<Customer> getAllCustomers() {


        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()){
            ResultSet resultSet;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){

                    Customer customer = mapCustomer(resultSet);
                    customers.add(customer);
                }
            }

                return  customers;
        } catch (SQLException e) {
            throw new CustomerException(FETCHING_CUSTOMER_ERROR);
        }
    }

    public void save(Customer customer){

        try (Connection connection = DataBaseConnection.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_CUSTOMER);
            setCustomer(preparedStatement,customer);

        } catch (SQLException e) {
            throw new CustomerException(INSERT_CUSTOMER_ERROR);
        }

    }

    public void update(Customer customer){

        try (Connection connection = DataBaseConnection.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_CUSTOMER);
            updateCustomer(preparedStatement,customer);

        } catch (SQLException e) {
            throw new CustomerException(UPDATE_CUSTOMER_ERROR);
        }


    }

    public void delete(Customer customer){

        try (Connection connection = DataBaseConnection.getConnection()){
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(DELETE_CUSTOMER)) {
                preparedStatement.setLong(1, customer.getId());
            }

        } catch (SQLException e) {
            throw new CustomerException(DELETE_CUSTOMER_ERROR);
        }

    }


    // Helper methods

    public void setCustomer(PreparedStatement preparedStatement,Customer customer){

        try {
            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3,customer.getSurname());
            preparedStatement.setDate(4, customer.getBirthday());
            preparedStatement.setString(5,customer.getCountry());
            preparedStatement.setString(6,customer.getZipcode());
            preparedStatement.setString(7,customer.getCity());
            preparedStatement.setString(8,customer.getStreet());
            preparedStatement.setInt(9,customer.getHousenumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new CustomerException(INSERT_CUSTOMER_ERROR);
        }


    }

    public void updateCustomer(PreparedStatement preparedStatement,Customer customer) {

        try {
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setDate(4, customer.getBirthday());
            preparedStatement.setString(5, customer.getCountry());
            preparedStatement.setString(6, customer.getZipcode());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getStreet());
            preparedStatement.setInt(9, customer.getHousenumber());
            preparedStatement.setLong(10, customer.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new CustomerException(UPDATE_CUSTOMER_ERROR);
        }
    }


    public Customer mapCustomer(ResultSet resultSet){

       Customer customer = new Customer();


        try {
            customer.setId(resultSet.getLong("id"));
            customer.setName(resultSet.getString("name"));
            customer.setSurname(resultSet.getString("surname"));
            customer.setUsername(resultSet.getString("username"));
            customer.setBirthday(resultSet.getDate("birthday"));
            customer.setCountry(resultSet.getString("country"));
            customer.setZipcode(resultSet.getString("zipcode"));
            customer.setCity(resultSet.getString("city"));
            customer.setStreet(resultSet.getString("street"));
            customer.setHousenumber(resultSet.getInt("housenumber"));
        } catch (SQLException e) {
            throw new CustomerException(FETCHING_CUSTOMER_ERROR);
        }
        return customer;
    }

}
