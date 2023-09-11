package org.akad.mhayo.orm_project.mybatis_module.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MeasurementConfig.class)
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllItemsTest() throws Exception {
        mockMvc.perform(get("/api/item"))
                .andExpect(status().isOk());
    }


    @Test
    public void getItemByIdTest() throws Exception {
        mockMvc.perform(get("/api/item/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveItemTest() throws Exception {

        String itemJson = "{\n" +
                "    \"name\": \"item1\",\n" +
                "    \"price\": 10.0,\n" +
                "    \"description\": \"item1 description\"\n" +
                "}";

        mockMvc.perform(post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateItemTest() throws Exception {

        String itemJson = "{\n" +
                "    \"name\": \"New\",\n" +
                "    \"price\": 10.0,\n" +
                "    \"description\": \"item1 description\"\n" +
                "}";

        mockMvc.perform(put("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteItemTest() throws Exception {
        mockMvc.perform(delete("/api/item/50"))
                .andExpect(status().isOk());
    }
}
