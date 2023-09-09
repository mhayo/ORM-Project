package org.akad.mhayo.orm_project.mybatis_module.mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import org.akad.mhayo.orm_project.mybatis_module.model.Item;
@Mapper
public interface ItemMapper {


    @Select("SELECT * FROM ITEM")
    List<Item> getAllItems();

    @Select("SELECT * FROM ITEM WHERE id = #{id}")
    Item getItemById(@Param("id")long id);

    @Insert("INSERT INTO ITEM (description, price) VALUES ( #{description}, #{price} )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Item item);


    @Update("UPDATE ITEM SET description = #{description},price = #{price} WHERE id = #{id}")
    void updateItem(Item item);

    @Delete("DELETE FROM ITEM WHERE id = #{id}")
    void deleteItem(@Param("id") long id);

}