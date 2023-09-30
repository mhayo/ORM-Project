package org.akad.mhayo.orm_project.jdbc_module.service;


import org.akad.mhayo.orm_project.jdbc_module.dao.ItemDAO;
import org.akad.mhayo.orm_project.jdbc_module.model.Item;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {
    
    @Autowired
    ItemDAO itemDAO; 
    
    long queryStart;
    long queryEnd;

    public Item getItemById(long id){

        queryStart = System.currentTimeMillis();
        Item temp = itemDAO.getItemById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","getItemById",queryEnd-queryStart);

        return temp;
    }


    public List<Item> getAllItems() {

        queryStart = System.currentTimeMillis();
        List<Item> temp = itemDAO.getAllItems();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","getAllItems",queryEnd-queryStart);

        return temp;

    }

    public void save(Item item){

        queryStart = System.currentTimeMillis();
        itemDAO.save(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","saveItem",queryEnd-queryStart);

    }

    public void update(Item item){

        queryStart = System.currentTimeMillis();
        itemDAO.update(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","updateItem",queryEnd-queryStart);

    }

    public void delete(long id){

        queryStart = System.currentTimeMillis();
        itemDAO.delete(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","deleteItem",queryEnd-queryStart);

    }

}
