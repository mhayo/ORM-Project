package org.akad.mhayo.orm_project.mybatis_module.mapper;

import org.akad.mhayo.orm_project.mybatis_module.model.Cart;
import org.akad.mhayo.orm_project.mybatis_module.model.Customer;
import org.akad.mhayo.orm_project.mybatis_module.model.Item;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CartMapper {

    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "customer", column = "customer_id",
                    one = @One(select = "getCustomerById")),
            @Result(property = "items", column = "id",
                    many = @Many(select = "getItemsByCartId",fetchType = FetchType.LAZY))
    })
    @Select("SELECT * FROM Cart")
    List<Cart> getAllCarts();

    @Select("SELECT * FROM Customer WHERE id = #{id}")
    Customer getCustomerById(long id);

    @Select("SELECT * FROM Item join Cart_Item on Cart_Item.item_id = Item.id  WHERE Cart_Item.cart_id = #{cartId}")
    List<Item> getItemsByCartId(long cartId);

    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "customer", column = "customer_id"
                    , one = @One(select = "getCustomerById")),
            @Result(property = "items", column = "id"
                    , many = @Many(select = "getItemsByCartId"))
    })
    @Select("SELECT * FROM Cart WHERE id = #{id}")
    Cart getCartById(@Param("id") long id);

    @Insert("INSERT INTO Cart(customer_id) VALUES(#{customer.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertCart(Cart cart);

    @Update("UPDATE Cart SET customer_id = #{customer.id} WHERE id = #{id}")
    void updateCart(Cart cart);

    @Delete("DELETE FROM Cart WHERE id = #{id}")
    void deleteCart(@Param("id") long id);

}