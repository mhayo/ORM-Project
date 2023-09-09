package org.akad.mhayo.orm_project.mybatis_module.controller;

import org.akad.mhayo.orm_project.mybatis_module.dto.ItemDTO;
import org.akad.mhayo.orm_project.mybatis_module.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.akad.mhayo.orm_project.mybatis_module.model.Item;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/item")
    public List<ItemDTO> getAllItems(){

        return itemService.getAllItems().stream().map(this::convertItemEntityToDTO).toList();
    }

    @GetMapping("/item/{id}")
    public ItemDTO getItemById(@PathVariable long id){

        return convertItemEntityToDTO(itemService.getItemById(id));
    }


    @PostMapping("/item")
    public void saveItem(@RequestBody ItemDTO itemDTO){

        itemService.save(convertItemDTOtoEntity(itemDTO));

    }

    @PutMapping("/item")
    public void updateItem(@RequestBody ItemDTO itemDTO){

        itemService.update(convertItemDTOtoEntity(itemDTO));
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable long id){

        itemService.delete(id);
    }

    // DTO Conversion

    public ItemDTO convertItemEntityToDTO(Item item){

        ItemDTO itemDTO = new ItemDTO();

        BeanUtils.copyProperties(item,itemDTO);

        return itemDTO;
    }

    public Item convertItemDTOtoEntity(ItemDTO itemDTO){

        Item item = new Item();

        BeanUtils.copyProperties(itemDTO,item);

        return item;
    }


}
