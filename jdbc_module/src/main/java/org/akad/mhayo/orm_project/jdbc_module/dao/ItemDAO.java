package org.akad.mhayo.orm_project.jdbc_module.dao;


import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.model.Item;
import org.akad.mhayo.orm_project.util.exceptions.ItemException;
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
public class ItemDAO {


    public ItemDAO(){
        // Standard constructor
    }


    public List<Item> getAllItems(){

        List<Item> items = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()){

            ResultSet resultSet;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS)) {
                resultSet = preparedStatement.executeQuery();
            }

            while(resultSet.next()){

                items.add(mapItem(resultSet));

            }

            return items;

        } catch (SQLException e) {
            throw new ItemException(FETCHING_ITEM_ERROR);
        }

    }


    public Item getItemById(long id){


        try (Connection connection = DataBaseConnection.getConnection()) {

            ResultSet resultSet;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID)) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }

            Item item = new Item();

            while (resultSet.next()){

                item = mapItem(resultSet);
            }

            return item;

        } catch (SQLException e) {
            throw new ItemException(FETCHING_ITEM_ERROR);
        }

    }


    public void save(Item item ){


        try (Connection connection = DataBaseConnection.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM);
            setItem(preparedStatement,item);

        } catch (SQLException e) {
            throw new ItemException(INSERT_ITEM_ERROR);
        }

    }


    public void update(Item item ){

        try (Connection connection = DataBaseConnection.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM);
            setItem(preparedStatement,item);

        } catch (SQLException e) {
            throw new ItemException(UPDATE_ITEM_ERROR);
        }

    }

    public void delete(long id ){


        try (Connection connection = DataBaseConnection.getConnection()){

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM)) {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new ItemException(DELETE_ITEM_ERROR);
        }

    }

    // helper methods

    private Item mapItem(ResultSet resultSet){

        Item item = new Item();

        try {
            item.setId(resultSet.getLong("id"));
            item.setDescription(resultSet.getString("description"));
            item.setPrice(resultSet.getDouble("price"));
        } catch (SQLException e) {
            throw new ItemException(INSERT_ITEM_ERROR);
        }

        return item;

    }

    private void setItem(PreparedStatement preparedStatement, Item item){

        try {
            preparedStatement.setString(1,item.getDescription());
            preparedStatement.setDouble(2,item.getPrice());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ItemException(INSERT_ITEM_ERROR);
        }

    }


}
