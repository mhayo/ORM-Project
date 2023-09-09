package org.akad.mhayo.orm_project.jpa_module.service;

import org.akad.mhayo.orm_project.jpa_module.model.Item;
import org.akad.mhayo.orm_project.jpa_module.repository.ItemRepository;
import org.akad.mhayo.orm_project.util.Measurement;
import org.akad.mhayo.orm_project.util.exceptions.ItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;


    long queryStart;
    long queryEnd;

    public List<Item> getAllItems() {

        queryStart = System.currentTimeMillis();
        List<Item> temp = itemRepository.findAll();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa", "getAllItems", queryEnd - queryStart);

        return temp;

    }

    public Item getItemById(long id) {

        Item item;
        queryStart = System.currentTimeMillis();

        Optional<Item> temp = itemRepository.findById(id);
        if (temp.isPresent()) {
            item = temp.get();
        } else {
            throw new ItemException("Item with not found with id: " + id);
        }

        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa", "getItemById", queryEnd - queryStart);

        return item;
    }

    public void save(Item item) {

        queryStart = System.currentTimeMillis();
        itemRepository.save(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa", "save_item", queryEnd - queryStart);


    }

    public void update(Item item) {

        queryStart = System.currentTimeMillis();
        itemRepository.save(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa", "update_item", queryEnd - queryStart);


    }

    public void delete(Item item) {

        queryStart = System.currentTimeMillis();
        itemRepository.delete(item);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa", "delete_item", queryEnd - queryStart);

    }

}
