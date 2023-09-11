package org.akad.mhayo.orm_project.jpa_module.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.akad.mhayo.orm_project.jpa_module.model.CartRequest;
import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MeasurementConfig.class)
public class CartControllerTest {

    @Autowired
    MockMvc mockMvc;

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
