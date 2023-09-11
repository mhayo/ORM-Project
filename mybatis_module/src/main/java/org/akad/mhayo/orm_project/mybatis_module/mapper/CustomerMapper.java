package org.akad.mhayo.orm_project.mybatis_module.mapper;

import org.akad.mhayo.orm_project.mybatis_module.model.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper
{
    @Select("SELECT * FROM CUSTOMER")
    List<Customer> getAllCustomers();

    @Select("SELECT * FROM CUSTOMER WHERE id = #{id}")
    Customer getCustomerById(long id);

    @Insert("INSERT INTO CUSTOMER (username,name,surname,birthday,country,zipcode,city,street,housenumber) VALUES(#{username},#{name},#{surname},#{birthday},#{country},#{zipcode},#{city},#{street},#{housenumber})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Customer customer);

    @Update("UPDATE CUSTOMER SET username = #{username},name = #{name},surname = #{surname},birthday = #{birthday},country = #{country},zipcode = #{zipcode},city = #{city},street = #{street},housenumber = #{housenumber} WHERE id = #{id} ")
    void update(Customer customer);

    @Delete("DELETE FROM CUSTOMER WHERE id = #{id}")
    void delete(@Param("id") long id);

}