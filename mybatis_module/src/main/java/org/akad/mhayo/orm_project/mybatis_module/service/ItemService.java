package org.akad.mhayo.orm_project.mybatis_module.service;

import org.akad.mhayo.orm_project.mybatis_module.mapper.ItemMapper;
import org.akad.mhayo.orm_project.mybatis_module.model.Item;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemMapper itemMapper;

    long queryStart;
    long queryEnd;

    public List<Item> getAllItems(){

        queryStart = System.currentTimeMillis();
        List<Item> temp = itemMapper.getAllItems();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","getAllItems",queryEnd-queryStart);

        return temp;

    }

    public Item getItemById(long id){

        queryStart = System.currentTimeMillis();
        Item temp =  itemMapper.getItemById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","getItemById",queryEnd-queryStart);

        return temp;
    }

    public void save(Item item){

        queryStart = System.currentTimeMillis();
        itemMapper.insert(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","saveItem",queryEnd-queryStart);

    }

    public void update(Item item){

        queryStart = System.currentTimeMillis();
        itemMapper.updateItem(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","updateItem",queryEnd-queryStart);

    }

    public void delete(long id){

        queryStart = System.currentTimeMillis();
        itemMapper.deleteItem(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","deleteItem",queryEnd-queryStart);

    }

}