package org.akad.mhayo.orm_project.jdbc_module.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.datasource.InitDataBase;
import org.akad.mhayo.orm_project.jdbc_module.model.CartRequest;
import org.akad.mhayo.orm_project.jdbc_module.service.CartService;
import org.akad.mhayo.orm_project.jdbc_module.service.ItemService;
import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.akad.mhayo.orm_project.util.exceptions.DataBaseConnectException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Connection;

import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.CONNECTION_ERROR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MeasurementConfig.class)
public class CartControllerTest {

 @Autowired
     MockMvc mockMvc;

 @BeforeAll
    public void setUp() throws Exception {

     Logger logger = org.slf4j.LoggerFactory.getLogger(CartControllerTest.class);

     logger.info("Setting up database");

     try {
         Connection connection = DataBaseConnection.getConnection();
         InitDataBase.dbInit(connection);
     } catch (Exception e) {
         throw new DataBaseConnectException(CONNECTION_ERROR);
     }
    }
 @Test
    public void getAllCartsTest() throws Exception {

     mockMvc.perform(get("/api/cart"))
                .andExpect(status().isOk());

    }



    @Test
    public void addToCartTest() throws Exception {

        CartRequest cartRequest = new CartRequest();
        cartRequest.setCartid(1);
        cartRequest.setItemid(3);
        cartRequest.setQuantity(2);
        cartRequest.setUsername("Max");
        ObjectMapper objectMapper = new ObjectMapper();
          String json = objectMapper.writeValueAsString(cartRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }



}
