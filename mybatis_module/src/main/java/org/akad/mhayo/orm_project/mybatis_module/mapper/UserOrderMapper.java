package org.akad.mhayo.orm_project.mybatis_module.mapper;

import org.akad.mhayo.orm_project.mybatis_module.model.UserOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserOrderMapper {


    @Select("SELECT * FROM UserOrder")
    List<UserOrder> getAllOrders();

    @Select("SELECT * FROM UserOrder WHERE id = #{id}")
    UserOrder getOrderById(@Param("id") long id);

    @Insert("INSERT INTO UserOrder(customer_id, sum) VALUES(#{customer.id}, #{sum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(UserOrder userOrder);

    @Update("UPDATE UserOrder SET customer_id = #{customer.id}, sum = #{sum} WHERE id = #{id}")
    void update(UserOrder userOrder);

    @Delete("DELETE FROM UserOrder WHERE id = #{id}")
    void delete(@Param("id") long id);


}